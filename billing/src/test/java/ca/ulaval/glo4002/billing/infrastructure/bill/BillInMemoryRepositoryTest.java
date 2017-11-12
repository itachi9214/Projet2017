package ca.ulaval.glo4002.billing.infrastructure.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;

@RunWith(MockitoJUnitRunner.class)
public class BillInMemoryRepositoryTest {

  private static final long CLIENT_ID = 1L;
  private static final Identity EXISTING_BILL_NUMBER = new Identity(100L);
  private static final Identity OTHER_EXISTING_BILL_NUMBER = new Identity(102L);
  private static final Identity NOT_EXISTING_BILL_NUMBER = new Identity(200L);

  private BillRepository billInMemory;

  @Mock
  private Bill bill;
  @Mock
  private Bill olderBill;

  @Before
  public void setUp() {
    billInMemory = new BillInMemoryRepository();
    willReturn(EXISTING_BILL_NUMBER).given(bill).getBillNumber();
  }

  @Test
  public void givenBillWhenCreateBillThenBillIsFound() {
    billInMemory.createBill(bill);

    assertEquals(bill, billInMemory.findById(EXISTING_BILL_NUMBER));
  }

  @Test(expected = BillAlreadyExistsException.class)
  public void givenBillWhenCreateExistingBillThenThrowException() {
    billInMemory.createBill(bill);
    billInMemory.createBill(bill);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenNotExistingBillNumberWhenFindByIdThenThrowException() {
    billInMemory.findById(NOT_EXISTING_BILL_NUMBER);
  }

  @Test
  public void whenUpdateBillThenBillIsFound() {
    billInMemory.createBill(bill);
    billInMemory.updateBill(bill);

    assertEquals(bill, billInMemory.findById(EXISTING_BILL_NUMBER));
  }

  @Test
  public void whenFindOldestUnpaidBillByClientIdThenClientIsTheSame() {
    willReturn(CLIENT_ID).given(bill).getClientId();
    billInMemory.createBill(bill);
    Bill billFound = billInMemory.findOldestUnpaidBillByClientId(CLIENT_ID);

    assertEquals(CLIENT_ID, billFound.getClientId().longValue());
  }

  @Test
  public void givenTwoBillsWhenFindOldestUnpaidBillByClientIdThenOldestIsReturned() {
    willReturn(CLIENT_ID).given(bill).getClientId();
    willReturn(CLIENT_ID).given(olderBill).getClientId();
    willReturn(EXISTING_BILL_NUMBER).given(bill).getBillNumber();
    willReturn(OTHER_EXISTING_BILL_NUMBER).given(olderBill).getBillNumber();
    willReturn(LocalDateTime.of(2017, 01, 01, 1, 1)).given(olderBill).getExpectedPayment();
    willReturn(LocalDateTime.of(2017, 11, 01, 1, 1)).given(bill).getExpectedPayment();
    billInMemory.createBill(olderBill);
    billInMemory.createBill(bill);

    Bill billFound = billInMemory.findOldestUnpaidBillByClientId(CLIENT_ID);

    assertEquals(OTHER_EXISTING_BILL_NUMBER, billFound.getBillNumber());
  }

  @Test(expected = BillNotFoundException.class)
  public void givenUnexistingBillWhenFindOldestUnpaidBillByClientIdThenThrowsException() {
    billInMemory.findOldestUnpaidBillByClientId(CLIENT_ID);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenUnexistingBillWhenCancelBillThenThrowsException() {
    billInMemory.cancelBill(NOT_EXISTING_BILL_NUMBER);
  }

  @Test(expected = BillNotFoundException.class)
  public void whenCancelBillThenThrowsExceptionOnFind() {
    billInMemory.createBill(bill);
    billInMemory.cancelBill(EXISTING_BILL_NUMBER);

    billInMemory.findById(EXISTING_BILL_NUMBER);
  }

}
