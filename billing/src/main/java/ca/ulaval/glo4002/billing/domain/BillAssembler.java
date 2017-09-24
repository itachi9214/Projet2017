package ca.ulaval.glo4002.billing.domain;

import java.util.UUID;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.api.dto.ResponseBillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;

public class BillAssembler {

  public ResponseBillDto create(Bill bill) {
    ResponseBillDto responseBillDto = new ResponseBillDto();
    responseBillDto.setBillNumber(bill.getBillNumber());
    responseBillDto.setTotal(bill.getBillTotal());
    responseBillDto.setDueTerm(bill.getDueTerm());
    responseBillDto.setUrl("/bills/" + bill.getBillNumber());
    return responseBillDto;
  }

  public Bill create(RequestBillDto requestBillDto) {
    Bill bill = new Bill();
    bill.setBillNumber(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    bill.setDueTerm(requestBillDto.getDueTerm());
    bill.setClientId(requestBillDto.getClientId());
    bill.setProducts(requestBillDto.getItems());
    bill.calculateBill();
    return bill;
  }

}
