package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;

@Path("/bills")
public interface BillingResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void save(RequestBillDto requestBillDto);

}
