package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;
import ca.ulaval.glo4002.billing.domain.Submission.IdFactory;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;
  private IdFactory idFactory;

  public BillService(BillRepository billRepository, BillAssembler billAssembler,
      SubmissionRepository submissionRepository) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.submissionRepository = submissionRepository;
    this.idFactory = new IdFactory();
  }

  public BillDto createBill(long billNumber) {
    Submission submission = submissionRepository
        .findSubmissionById(idFactory.createIdFromNumber(billNumber));
    Bill bill = new Bill(submission.getBillNumber(), submission.getDueTerm(),
        submission.getClientId(), submission.getItems());
    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill);
  }

}
