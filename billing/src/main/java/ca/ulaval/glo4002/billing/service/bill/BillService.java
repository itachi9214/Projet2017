package ca.ulaval.glo4002.billing.service.bill;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.BillState;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;
  private IdentityFactory identityFactory;

  public BillService() {
    this.billRepository = ServiceLocator.getService(BillRepository.class);
    this.billAssembler = ServiceLocator.getService(BillAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.identityFactory = ServiceLocator.getService(IdentityFactory.class);
  }

  public BillService(IdentityFactory identityFactory, BillAssembler billAssembler,
      SubmissionRepository submissionRepository, BillRepository billRepository) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.submissionRepository = submissionRepository;
    this.identityFactory = identityFactory;
  }

  public BillDto createBill(long billNumber) {
    Identity identity = identityFactory.createIdFromNumber(billNumber);

    Submission submission = submissionRepository.findSubmissionById(identity);
    Bill bill = billAssembler.createBillFromSubmission(submission);

    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill);
  }

  public void cancelBill(long billNumber) {
    Identity identity = identityFactory.createIdFromNumber(billNumber);

    Bill billToCancel = billRepository.findById(identity);
    billToCancel.setBillState(BillState.PAID);
    BigDecimal canceledBillPaidAmount = billToCancel.getPaidAmount();
    if (canceledBillPaidAmount.compareTo(new BigDecimal(0)) == 1) {
      payOtherBills(canceledBillPaidAmount, identity);
    }
  }

  protected void payOtherBills(BigDecimal canceledBillPaidAmount, Identity identity) {
    while (!canceledBillPaidAmount.equals(new BigDecimal(0))) {
      Bill bill = billRepository.findOldestUnpaidBillOfSameClient(identity);
      if (bill.calculateUnpaidAmount().compareTo(canceledBillPaidAmount) == 1) {
        bill.setPaidAmount(canceledBillPaidAmount.add(bill.getPaidAmount()));
        canceledBillPaidAmount = new BigDecimal(0);
      } else {
        bill.setPaidAmount(bill.getTotalPrice());
        canceledBillPaidAmount = canceledBillPaidAmount.subtract(bill.calculateUnpaidAmount());
      }
    }
  }

}
