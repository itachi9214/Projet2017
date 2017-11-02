package ca.ulaval.glo4002.payment.domain.bill;

import java.util.List;

public interface BillRepository {

  public List<Bill> getUnpaidBillsOrderedByOldestForClient(Long clientId);

  public void saveBillStateToPaid(Bill bill);

}
