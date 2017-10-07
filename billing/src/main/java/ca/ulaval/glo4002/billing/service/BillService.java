package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;

  public BillService(BillRepository billRepository, BillAssembler billAssembler) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
  }

  public BillDto createBill(RequestBillDto requestBillDto) {
    Submission submission = submissionRepository.findSubmission(requestBillDto);
    Bill bill = billAssembler.disassembleBill(requestBillDto);
    billRepository.createBill(bill);
    return billAssembler.assembleBill(bill, submission);
  }

}
