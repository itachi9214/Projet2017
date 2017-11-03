package ca.ulaval.glo4002.billing.service.bill;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillStateDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

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

  public List<BillStateDto> assembleBillState(List<Bill> bills) {
    List<BillStateDto> billStateDtos = new ArrayList<>();

    for (Bill bill : bills) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
      BillStateDto billStateDto = new BillStateDto(bill.getBillNumber().getNumber(),
          bill.getClientId(), bill.getEffectiveDate().format(formatter), bill.getTotalPrice(),
          bill.getBillState());
      billStateDtos.add(billStateDto);
    }
    return billStateDtos;
  }

  public Bill createBillFromSubmission(Submission submission) {
    Bill bill = new Bill(submission.getBillNumber(), submission.getDueTerm(),
        submission.getClientId(), submission.getItems());
    return bill;
  }

}
