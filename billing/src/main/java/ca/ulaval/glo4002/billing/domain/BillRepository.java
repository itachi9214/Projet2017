package ca.ulaval.glo4002.billing.domain;

import ca.ulaval.glo4002.billing.domain.bill.Bill;

public interface BillRepository {

  public void createBill(Bill bill);

}
