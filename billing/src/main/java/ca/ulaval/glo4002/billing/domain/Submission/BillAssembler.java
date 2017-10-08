package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;

public class BillAssembler {

  private IdFactory idFactory;

  public BillAssembler() {
    idFactory = new IdFactory();
  }

  public BillDto assembleBill(Bill bill, Submission submission) {
    BillDto billDto = new BillDto(submission.billNumber.getNumber(), bill.getEffectiveDate(),
        bill.calculateExpectedPaiementDate(), bill.dueTerm,
        "/bills/" + bill.billNumber.getNumber());
    return billDto;
  }

  public Bill disassembleBill(RequestBillDto billDto) {
    Bill bill = new Bill(idFactory.createIdFromNumber(billDto.getBillNumber()));
    return bill;
  }

}