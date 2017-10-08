package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;
import ca.ulaval.glo4002.billing.domain.Submission.IdFactory;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.SubmissionInMemory;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;
  private IdFactory idFactory;

  public BillService(BillRepository billRepository, BillAssembler billAssembler) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.idFactory = new IdFactory();
    this.submissionRepository = new SubmissionInMemory();
  }

  public BillDto createBill(RequestBillDto requestBillDto) {
    Submission submission = submissionRepository
        .findSubmission(idFactory.createIdFromNumber(requestBillDto.getBillNumber()));
    Bill bill = billAssembler.disassembleBill(requestBillDto);
    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill, submission);
  }

}
