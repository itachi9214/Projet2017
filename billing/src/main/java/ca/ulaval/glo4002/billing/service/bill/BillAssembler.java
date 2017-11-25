package ca.ulaval.glo4002.billing.service.bill;

import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;

public class BillAssembler {

  private static final String BASE_URL = "/bills/";

  public BillAssembler() {
  }

  public BillDto assembleBill(Bill bill) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
    BillDto billDto = new BillDto(bill.getBillNumber().getNumber(),
        bill.getEffectiveDate().format(formatter), bill.getExpectedPayment().format(formatter),
        bill.getDueTerm(), BASE_URL + bill.getBillNumber().getNumber());
    return billDto;
  }

  public BillForPaymentDto assembleBillForPayment(Bill bill) {
    BillForPaymentDto billStateDto = new BillForPaymentDto(bill.getBillNumber().getNumber(),
        bill.getRemainingAmount());
    return billStateDto;
  }

  public Bill createBillFromSubmission(Submission submission) {
    Bill bill = new Bill(submission.getBillNumber(), submission.getDueTerm(),
        submission.getClientId(), submission.getItems());
    return bill;
  }

}
