package ca.ulaval.glo4002.billing.service.bill;

import java.util.List;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillStateDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
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

  public BillStateDto getUnpaidBillsOrderedByOldest(Long clientId) {
    List<Bill> bills = billRepository.findAllByClientId(clientId);
    return billAssembler.assembleBillState(bills);
  }

}
