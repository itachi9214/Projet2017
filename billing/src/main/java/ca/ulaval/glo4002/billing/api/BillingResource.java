package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.bill.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.service.BillService;

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
    try {
      billService.getClientByIdInCrm(requestBillDto.getClientId());
      for (OrderedProduct item : requestBillDto.getItems()) {
        billService.getProductByIdInCrm(item.getProductId());
      }

      return Response.status(Response.Status.CREATED).entity(billService.createBill(requestBillDto))
          .build();
    } catch (ProductNotFoundException exception) {
      return Response.status(Status.BAD_REQUEST).build();
    } catch (ClientNotFoundException exeption) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

}
