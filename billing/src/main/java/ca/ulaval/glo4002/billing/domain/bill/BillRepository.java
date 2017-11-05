package ca.ulaval.glo4002.billing.domain.bill;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;
import ca.ulaval.glo4002.billing.infrastructure.bill.BillNotFoundException;

public interface BillRepository {

  public void createBill(Bill bill) throws BillAlreadyExistsException;

  public Bill findById(Identity billNumber) throws BillNotFoundException;

  public void cancelBill(long billNumber) throws BillNotFoundException;

}
