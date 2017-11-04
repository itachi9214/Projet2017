package ca.ulaval.glo4002.payment.http;

import ca.ulaval.glo4002.payment.api.dto.bill.BillDto;

public interface BillHttpRepository {

  public BillDto getOldestUnpaidBillForClient(Long clientNumber);

}
