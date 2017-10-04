package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;

public class BillInMemory implements BillRepository {

  private Map<Long, Bill> bills = new HashMap<>();
  SubmissionInMemory submissionInMemory = new SubmissionInMemory();

  public BillInMemory(Map<Long, Bill> bills) {
    this.bills = bills;
  }

  @Override
  public void createBill(Bill bill) {
    try {
      submissionInMemory.getSubmissionById(bill.getBillNumber());
      bills.put(bill.getBillNumber(), bill);
    } catch (SubmissionNotFoundException e) {
      e.printStackTrace();
    }
  }

}
