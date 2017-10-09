package ca.ulaval.glo4002.billing.domain.Submission;

import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;

public class BillAssembler {

  public BillAssembler() {
  }

  public BillDto assembleBill(Bill bill) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
    BillDto billDto = new BillDto(bill.billNumber.getNumber(),
        bill.getEffectiveDate().format(formatter), bill.getExpectedPaiement().format(formatter),
        bill.getDueTerm(), "/bills/" + bill.billNumber.getNumber());
    return billDto;
  }

}