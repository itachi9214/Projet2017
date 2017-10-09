package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;
import ca.ulaval.glo4002.billing.domain.Submission.Id;

public class BillInMemory implements BillRepository {

  private Map<Id, Bill> bills = new HashMap<>();

  public BillInMemory() {
  }

  public BillInMemory(Map<Id, Bill> bills) {
    this.bills = bills;
  }

  @Override
  public void createBill(Bill bill) {
    if (billAlreadyExists(bill.getBillNumber())) {
      throw new BillAlreadyExistsException();
    }
    bills.put(bill.getBillNumber(), bill);
  }

  private boolean billAlreadyExists(Id billId) {
    return bills.containsKey(billId);
  }

}
