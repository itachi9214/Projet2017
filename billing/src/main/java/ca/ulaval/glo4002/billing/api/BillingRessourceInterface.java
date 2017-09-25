package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;

public interface BillingRessourceInterface {
  public Response createBill(RequestBillDto requestBillDto);
}
