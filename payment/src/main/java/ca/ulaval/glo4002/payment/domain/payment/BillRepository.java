package ca.ulaval.glo4002.payment.domain.payment;

import java.util.List;

import ca.ulaval.glo4002.payment.domain.bill.Bill;

public interface BillRepository {

  public List<Bill> getUnpaidBillsOrderedByOldestForClient(Long clientId);

  public void saveBillStateToPaid(Bill bill);

}
