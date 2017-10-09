package ca.ulaval.glo4002.billing.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;
import ca.ulaval.glo4002.billing.domain.Submission.Id;

@RunWith(MockitoJUnitRunner.class)
public class BillInMemoryTest {

  private static final Id EXISTING_BILL_NUMBER = new Id(200L);
  BillRepository billInMemory;
  private Map<Id, Bill> bills;

  @Mock
  Bill bill;

  @Before
  public void setUp() {
    bills = new HashMap<>();
    billInMemory = new BillInMemory(bills);
    when(bill.getBillNumber()).thenReturn(EXISTING_BILL_NUMBER);
  }

  @Test
  public void givenBillWhenCreateNotExistingBillThenBillsContainsBill() {
    billInMemory.createBill(bill);

    assertEquals(bills.containsKey(EXISTING_BILL_NUMBER), true);
  }

  @Test(expected = BillAlreadyExistsException.class)
  public void givenBillWhenCreateExistingBillThenThrowException() {
    billInMemory.createBill(bill);
    billInMemory.createBill(bill);
  }

}