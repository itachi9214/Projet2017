package ca.ulaval.glo4002.billing.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.service.bill.BillService;
import ca.ulaval.glo4002.billing.service.submission.SubmissionService;

@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {

  private SubmissionService submissionService;
  private BillService billService;

  public BillingResource() {
    this.submissionService = ServiceLocator.getService(SubmissionService.class);
    this.billService = ServiceLocator.getService(BillService.class);
  }

  public BillingResource(SubmissionService submissionService, BillService billService) {
    this.submissionService = submissionService;
    this.billService = billService;
  }

  @POST
  public Response createSubmission(RequestSubmissionDto requestSubmissionDto) {
    ResponseSubmissionDto responseSubmissionDto = submissionService
        .createSubmission(requestSubmissionDto);
    return Response.status(Response.Status.CREATED).entity(responseSubmissionDto).build();
  }

  @POST
  @Path("/{id}")
  public Response createBill(@PathParam("id") long id) {
    BillDto billDto = billService.createBill(id);
    return Response.status(Response.Status.OK).entity(billDto).build();
  }

  @GET
  @Path("/{client_id}")
  public Response getUnpaidBillsOrderedByOldestForClient(@PathParam("client_id") Long clientId) {
    BillForPaymentDto billStateDto = billService.getOldestUnpaidBillForClient(clientId);
    return Response.status(Response.Status.OK).entity(billStateDto).build();
  }

  @POST
  @Path("/update")
  public Response updateBillAfterPayment(BillForPaymentDto billForPaymentDto) {
    billService.updateBillAfterPayment(billForPaymentDto);
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("/{id}")
  public Response cancelBill(@PathParam("id") long id) {
    billService.cancelBill(id);
    return Response.status(Response.Status.ACCEPTED).build();
  }

}