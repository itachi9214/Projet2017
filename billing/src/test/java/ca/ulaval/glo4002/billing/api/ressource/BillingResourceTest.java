package ca.ulaval.glo4002.billing.api.ressource;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.ressource.BillingResource;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.service.bill.BillService;
import ca.ulaval.glo4002.billing.service.submission.SubmissionService;

@RunWith(MockitoJUnitRunner.class)
public class BillingResourceTest extends JerseyTest {

  private static final int A_NEGATIVE_QUANTITY = -3;
  private static final Long EXISTING_CLIENT = 2L;
  private static final Long NON_EXISTING_CLIENT = -6L;
  private static final int NON_EXISTING_PRODUCT = -4;
  private static final int EXISTING_PRODUCT = 1;
  private static final float A_PRICE = 15;
  private static final String A_NOTE = "note";
  private static final int A_QUANTITY = 2;

  private ClientDto clientDto;
  private ProductDto productDto;

  @Mock
  private SubmissionService submissionService;

  @Mock
  private BillService billService;

  @Override
  public Application configure() {
    MockitoAnnotations.initMocks(this);
    clientDto = new ClientDto();
    productDto = new ProductDto();

    willThrow(new ClientNotFoundException(NON_EXISTING_CLIENT)).given(submissionService)
        .getClientByIdInCrm(NON_EXISTING_CLIENT);
    willReturn(clientDto).given(submissionService).getClientByIdInCrm(EXISTING_CLIENT);
    willThrow(new ProductNotFoundException(NON_EXISTING_PRODUCT)).given(submissionService)
        .getProductByIdInCrm(NON_EXISTING_PRODUCT);
    willReturn(productDto).given(submissionService).getProductByIdInCrm(EXISTING_PRODUCT);

    return new ResourceConfig().register(new BillingResource(submissionService, billService));
  }

  @Test
  public void givenExistingClientAndProductWhenCreateSubmissionThenResponseStatusIsCreated()
      throws NegativeParameterException {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(new OrderedProduct(EXISTING_PRODUCT, A_PRICE, A_NOTE, A_QUANTITY));
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, items);
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request().post(requestEntity);

    assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenNonExistingClientWhenCreateSubmissionThenResponseStatusIsBadRequest() {
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(NON_EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, new ArrayList<>());
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request(MediaType.APPLICATION_JSON).post(requestEntity,
        Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenNonExistingProductWhenCreateSubmissionThenResponseStatusIsBadRequest()
      throws NegativeParameterException {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(new OrderedProduct(NON_EXISTING_PRODUCT, A_PRICE, A_NOTE, A_QUANTITY));
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, items);
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request(MediaType.APPLICATION_JSON).post(requestEntity,
        Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Ignore
  @Test
  public void givenProductWithNegativeQuantityWhenCreateSubmissionThenResponseStatusIsBadRequest()
      throws NegativeParameterException {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(new OrderedProduct(EXISTING_PRODUCT, A_PRICE, A_NOTE, A_NEGATIVE_QUANTITY));
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, items);
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);
    willThrow(new NegativeParameterException("Product quantity")).given(submissionService)
        .createSubmission(requestSubmissionDto);

    Response response = target("/bills").request(MediaType.APPLICATION_JSON).post(requestEntity,
        Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

}
