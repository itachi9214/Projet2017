package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;

public class BillAssembler {

  public BillAssembler() {
  }

  public BillDto assembleBill(Bill bill) {
    BillDto billDto = new BillDto(bill.billNumber.getNumber(), bill.getEffectiveDate(),
        bill.getExpectedPaiement(), bill.getDueTerm(),
        "/bills/" + bill.billNumber.getNumber());
    return billDto;
  }

}