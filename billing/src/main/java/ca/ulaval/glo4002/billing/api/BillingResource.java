package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.service.SubmissionService;

@Path("/bills")
public class BillingResource {

  private SubmissionService submissionService;

  public BillingResource() {
    submissionService = new SubmissionService();
  }

  public BillingResource(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createSubmission(RequestSubmissionDto requestSubmissionDto) {
    try {
      ClientDto clientDto = submissionService
          .getClientByIdInCrm(requestSubmissionDto.getClientId());
      for (OrderedProduct item : requestSubmissionDto.getItems()) {
        submissionService.getProductByIdInCrm(item.getProductId());
      }

      if (requestSubmissionDto.getDueTerm() == null) {
        requestSubmissionDto.setDueTerm(clientDto.getDefaultDueTerm());
      }

      return Response.status(Response.Status.CREATED)
          .entity(submissionService.createSubmission(requestSubmissionDto)).build();
    } catch (ProductNotFoundException exception) {
      return Response.status(Status.BAD_REQUEST).build();
    } catch (ClientNotFoundException exeption) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

}
