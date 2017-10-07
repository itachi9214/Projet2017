package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;

public class BillAssembler {

  public BillDto assembleBill(Bill bill, Submission submission) {
    BillDto billDto = new BillDto(submission.billNumber, bill.getEffectiveDate(),
        bill.calculateExpectedPaiementDate(), bill.dueTerm, "/bills/" + bill.billNumber);
    return billDto;
  }

  public Bill disassembleBill(RequestBillDto billDto) {
    Bill bill = new Bill(billDto.getBillNumber());
    return bill;
  }

}