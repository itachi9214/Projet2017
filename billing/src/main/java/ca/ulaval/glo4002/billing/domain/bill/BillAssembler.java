package ca.ulaval.glo4002.billing.domain.bill;

import java.util.UUID;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.api.dto.ResponseBillDto;

public class BillAssembler {

  public ResponseBillDto create(Bill bill) {
    ResponseBillDto responseBillDto = new ResponseBillDto(bill.getBillNumber(), bill.getBillTotal(),
        bill.getDueTerm(), "/bills/" + bill.getBillNumber());
    return responseBillDto;
  }

  public Bill create(RequestBillDto requestBillDto) {
    Bill bill = new Bill(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
        requestBillDto.getDueTerm(), requestBillDto.getClientId(), requestBillDto.getItems());
    bill.calculateBill();
    return bill;
  }

}
