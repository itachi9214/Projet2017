package ca.ulaval.glo4002.payment.domain.bill;

import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

public interface BillRepository {

  public Bill getOldestUnpaidBillForClient(Long clientId) throws BillNotFoundException;

  public void changeBillStateToPaid(Bill bill);

}
