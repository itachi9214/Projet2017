package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.BillAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.BillFactory;
import ca.ulaval.glo4002.billing.domain.Submission.BillRepository;

public class BillService {

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private BillFactory billFactory;
  private BillDto billDto;

  public BillService(BillRepository billRepository, BillAssembler billAssembler) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
  }

  public BillDto createBill() {
    Bill bill = billAssembler.desassembleBill(billFactory, billDto);
    billRepository.createBill(bill);
    return billAssembler.assebleBill(bill);
  }

}
