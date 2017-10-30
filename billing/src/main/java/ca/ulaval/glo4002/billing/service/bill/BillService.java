package ca.ulaval.glo4002.billing.service.bill;

import ca.ulaval.glo4002.billing.ServiceLocator;
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

  public BillService() {
    this.billRepository = ServiceLocator.getService(BillRepository.class);
    this.billAssembler = ServiceLocator.getService(BillAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.identityFactory = ServiceLocator.getService(IdentityFactory.class);
  }

  public BillDto createBill(long billNumber) {
    Submission submission = submissionRepository
        .findSubmissionById(identityFactory.createIdFromNumber(billNumber));
    Bill bill = billAssembler.createTheBillFromTheSubmissionData(submission);
    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill);
  }

}
