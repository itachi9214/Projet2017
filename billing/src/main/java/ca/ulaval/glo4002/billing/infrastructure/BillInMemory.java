package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.BillRepository;

public class BillInMemory implements BillRepository {
  private Map<Long, RequestBillDto> bills = new HashMap<>();

  @Override
  public void createBill(RequestBillDto bill) {
    bills.put(bill.getClientId(), bill);
  }

}
