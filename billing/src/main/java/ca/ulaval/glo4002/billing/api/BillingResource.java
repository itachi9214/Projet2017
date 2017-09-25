package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.BillService;

@Path("/bills")
public class BillingResource {

  private BillService billService;

  public BillingResource() {
    billService = new BillService();
  }

  public BillingResource(BillService billService) {
    this.billService = billService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createBill(RequestBillDto requestBillDto) {
    return Response.status(Response.Status.CREATED).entity(billService.createBill(requestBillDto))
        .build();
  }

}
