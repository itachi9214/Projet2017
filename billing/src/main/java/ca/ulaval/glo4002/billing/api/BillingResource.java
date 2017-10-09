package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.http.BillAlreadyExistsExceptionMapper;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ClientNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.http.NegativeParameterExceptionMapper;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.http.SubmissionNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.infrastructure.BillAlreadyExistsException;
import ca.ulaval.glo4002.billing.infrastructure.SubmissionNotFoundException;
import ca.ulaval.glo4002.billing.service.BillService;
import ca.ulaval.glo4002.billing.service.SubmissionService;

@Path("/bills")
public class BillingResource {

  private SubmissionService submissionService;
  private BillService billService;
  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;
  private ProductNotFoundExceptionMapper productNotFoundExceptionMapper;
  private NegativeParameterExceptionMapper negativeParameterExceptionMapper;
  private BillAlreadyExistsExceptionMapper billAlreadyExistsExceptionMapper;
  private SubmissionNotFoundExceptionMapper submissionNotFoundExceptionMapper;

  public BillingResource(SubmissionService submissionService, BillService billService) {
    this.submissionService = submissionService;
    this.billService = billService;
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper();
    productNotFoundExceptionMapper = new ProductNotFoundExceptionMapper();
    negativeParameterExceptionMapper = new NegativeParameterExceptionMapper();
    billAlreadyExistsExceptionMapper = new BillAlreadyExistsExceptionMapper();
    submissionNotFoundExceptionMapper = new SubmissionNotFoundExceptionMapper();
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

      submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto,
          clientDto.getDefaultDueTerm());

      return Response.status(Response.Status.CREATED)
          .entity(submissionService.createSubmission(requestSubmissionDto)).build();
    } catch (ProductNotFoundException productNotFoundException) {
      return productNotFoundExceptionMapper.toResponse(productNotFoundException);
    } catch (ClientNotFoundException clientNotFoundException) {
      return clientNotFoundExceptionMapper.toResponse(clientNotFoundException);
    } catch (NegativeParameterException negativeParameterException) {
      return negativeParameterExceptionMapper.toResponse(negativeParameterException);
    }
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response createBill(@PathParam("id") long id) {
    try {
      return Response.status(Response.Status.CREATED).entity(billService.createBill(id)).build();
    } catch (SubmissionNotFoundException submissionNotFoundException) {
      return submissionNotFoundExceptionMapper.toResponse(submissionNotFoundException);
    } catch (BillAlreadyExistsException billAlreadyExistsException) {
      return billAlreadyExistsExceptionMapper.toResponse(billAlreadyExistsException);
    }
  }

}
