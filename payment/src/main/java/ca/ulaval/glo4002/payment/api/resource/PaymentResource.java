package ca.ulaval.glo4002.payment.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.service.PaymentService;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

  private PaymentService paymentService;

  public PaymentResource() {
    this.paymentService = ServiceLocator.getService(PaymentService.class);
  }

  public PaymentResource(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @POST
  public Response makePayment(RequestPaymentDto requestPaymentDto) {
    ResponsePaymentDto responsePaymentDto = paymentService.makePayment(requestPaymentDto);
    return Response.status(Response.Status.CREATED).entity(responsePaymentDto).build();
  }

}
