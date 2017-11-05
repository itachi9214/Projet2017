package ca.ulaval.glo4002.payment.domain.bill;

public interface BillRepository {

  public Bill getOldestUnpaidBillForClient(Long clientId);

  public void changeBillStateToPaid(Bill bill);

}
