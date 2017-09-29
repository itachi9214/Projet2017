package ca.ulaval.glo4002.billing.infrastructure;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.bill.Bill;

@RunWith(MockitoJUnitRunner.class)
public class BillInMemoryTest {

  public BillInMemory billInMemory;

  @Mock
  Bill bill;

  @Before
  public void setUp() {
    billInMemory = new BillInMemory();
  }

  @Test
  public void givenBillWhenCreateBillPutBill() {
    billInMemory.createBill(bill);

    verify(bill).getClientId();
  }

}