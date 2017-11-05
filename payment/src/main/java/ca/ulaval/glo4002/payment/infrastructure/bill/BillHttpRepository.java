package ca.ulaval.glo4002.payment.infrastructure.bill;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.http.BillingHttp;

public class BillHttpRepository implements BillRepository {

  private BillingHttp billingHttp;

  public BillHttpRepository() {
    this(ServiceLocator.getService(BillingHttp.class));
  }

  public BillHttpRepository(BillingHttp billingHttp) {
    this.billingHttp = billingHttp;
  }

  @Override
  public Bill getOldestUnpaidBillForClient(Long clientId) throws BillNotFoundException {
    return billingHttp.getOldestUnpaidBillForClient(clientId);
  }

  @Override
  public void updateBillAfterPayment(Bill bill) {
    billingHttp.updateBillAfterPayment(bill);
  }

}
