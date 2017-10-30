package ca.ulaval.glo4002.billing.domain.bill;

import ca.ulaval.glo4002.billing.domain.identity.Identity;

public interface BillRepository {

  public void createBill(Bill bill);

  public Bill findByIdentity(Identity billNumber);

}
