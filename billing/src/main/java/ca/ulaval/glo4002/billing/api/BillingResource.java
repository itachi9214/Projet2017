package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ClientNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundExceptionMapper;
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
  public Response createSubmission(RequestSubmissionDto requestSubmissionDto,
      ClientNotFoundExceptionMapper clientNotFoundExceptionMapper,
      ProductNotFoundExceptionMapper productNotFoundExceptionMapper) {
    try {
      ClientDto clientDto = submissionService
          .getClientByIdInCrm(requestSubmissionDto.getClientId());
      for (OrderedProduct item : requestSubmissionDto.getItems()) {
        submissionService.getProductByIdInCrm(item.getProductId());
      }

      submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto,
          clientDto.getDefaultDueTerm());

      return Response.status(Response.Status.CREATED)
          .entity(submissionService.createSubmission(requestSubmissionDto)).build();
      
    } catch (ProductNotFoundException productNotFoundException) {
      return productNotFoundExceptionMapper.toResponse(productNotFoundException);
      
    } catch (ClientNotFoundException clientNotFoundException) {
      return clientNotFoundExceptionMapper.toResponse(clientNotFoundException)
    }
  }
  
}
