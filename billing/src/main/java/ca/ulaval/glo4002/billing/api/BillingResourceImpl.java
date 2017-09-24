package ca.ulaval.glo4002.billing.api;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.BillRepository;

public class BillingResourceImpl implements BillingResource {
  private BillRepository billRepository;

  @Override
  public void save(RequestBillDto requestBillDto) {
    billRepository.createBill(requestBillDto);
  }
}
