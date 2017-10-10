package ca.ulaval.glo4002.billing.service.bill;

import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

public class BillAssembler {

  public BillAssembler() {
  }

  public BillDto assembleBill(Bill bill) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
    BillDto billDto = new BillDto(bill.getBillNumber().getNumber(),
        bill.getEffectiveDate().format(formatter), bill.getExpectedPaiement().format(formatter),
        bill.getDueTerm(), "/bills/" + bill.getBillNumber().getNumber());
    return billDto;
  }

  public Bill createTheBillFromTheSubmissionData(Submission submission) {
    Bill bill = new Bill(submission.getBillNumber(), submission.getDueTerm(),
        submission.getClientId(), submission.getItems());
    return bill;
  }

}
