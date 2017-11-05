package ca.ulaval.glo4002.payment.infrastructure.bill;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.http.BillingHttp;

@RunWith(MockitoJUnitRunner.class)
public class BillHttpRepositoryTest {

  private static final long CLIENT_ID = 1L;

  private BillRepository billRepository;

  @Mock
  private BillingHttp billingHttp;
  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    billRepository = new BillHttpRepository(billingHttp);
  }

  @Test
  public void whenGetOldestUnpaidBillForClientThenVerifyBillIsFound() {
    billRepository.getOldestUnpaidBillForClient(CLIENT_ID);

    verify(billingHttp).getOldestUnpaidBillForClient(CLIENT_ID);
  }

  @Test
  public void whenSaveBillStateToPaidThenVerifyBillIsSaved() {
    billRepository.saveBillStateToPaid(bill);

    verify(billingHttp).saveBillStateToPaid(bill);
  }

}
