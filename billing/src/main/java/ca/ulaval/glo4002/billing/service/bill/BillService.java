package ca.ulaval.glo4002.billing.service.bill;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;
  private IdentityFactory identityFactory;

  public BillService(BillRepository billRepository, BillAssembler billAssembler,
      SubmissionRepository submissionRepository) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.submissionRepository = submissionRepository;
    this.identityFactory = new IdentityFactory();
  }

  public BillDto createBill(long billNumber) {
    Submission submission = submissionRepository
        .findSubmissionById(identityFactory.createIdFromNumber(billNumber));
    Bill bill = billAssembler.createTheBillFromTheSubmissionData(submission);
    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill);
  }

}
