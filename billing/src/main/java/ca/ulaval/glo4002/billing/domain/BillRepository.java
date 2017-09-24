package ca.ulaval.glo4002.billing.domain;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;

public interface BillRepository {

  public void createBill(RequestBillDto bill);

}
