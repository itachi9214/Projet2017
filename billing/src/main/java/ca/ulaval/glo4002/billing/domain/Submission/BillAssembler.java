package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;

public class BillAssembler {

  public BillDto assebleBill(Bill bill) {
    BillDto billDto = new BillDto(bill.billNumber, bill.getEffectiveDate(),
        bill.getExpectedPaiement(), bill.dueTerm, "/bills/" + bill.billNumber);
    return billDto;
  }

  public Bill desassembleBill(BillFactory billFactory, BillDto billDto) {
    return billFactory.create(billDto.getId(), billDto.getEffectiveDate(),
        billDto.getExpectedPayment(), billDto.getDueTerm());
  }

}