package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;

public class BillInMemory implements BillRepository {

  private Map<Long, Bill> bills = new HashMap<>();

  @Override
  public void createBill(Bill bill) {
    bills.put(bill.getClientId(), bill);
  }

}
