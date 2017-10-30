package ca.ulaval.glo4002.billing.infrastructure.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;

@RunWith(MockitoJUnitRunner.class)
public class BillInMemoryTest {

  private static final Identity EXISTING_BILL_NUMBER = new Identity(100L);
  private static final Identity NOT_EXISTING_BILL_NUMBER = new Identity(200L);;

  private BillRepository billInMemory;

  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    billInMemory = new BillInMemory();
    willReturn(EXISTING_BILL_NUMBER).given(bill).getBillNumber();
  }

  @Test
  public void givenBillWhenCreateBillThenFindBill() {
    billInMemory.createBill(bill);

    assertEquals(billInMemory.findById(EXISTING_BILL_NUMBER), bill);
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

}
