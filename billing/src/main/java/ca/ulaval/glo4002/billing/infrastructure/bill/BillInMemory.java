package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;

public class BillInMemory implements BillRepository {

  private Map<Identity, Bill> bills = new HashMap<>();

  public BillInMemory() {
  }

  @Override
  public void createBill(Bill bill) {
    if (billAlreadyExists(bill.getBillNumber())) {
      throw new BillAlreadyExistsException();
    }
    bills.put(bill.getBillNumber(), bill);
  }

  private boolean billAlreadyExists(Identity billId) {
    return bills.containsKey(billId);
  }

  @Override
  public Bill findById(Identity billNumber) {
    if (!bills.containsKey(billNumber)) {
      throw new BillNotFoundException();
    }
    return bills.get(billNumber);
  }

}
