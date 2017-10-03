package ca.ulaval.glo4002.billing.api;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.service.SubmissionService;

@RunWith(MockitoJUnitRunner.class)
public class BillingResourceTest extends JerseyTest {

  private static final Long EXISTING_CLIENT = 2L;
  private static final Long NON_EXISTING_CLIENT = -6L;
  private static final int NON_EXISTING_PRODUCT = -4;
  private static final int EXISTING_PRODUCT = 1;
  private static final float A_PRICE = 15;
  private static final String A_NOTE = "note";
  private static final int A_QUANTITY = 2;

  @Mock
  private SubmissionService submissionService;

  @Mock
  private ClientDto clientDto;

  @Mock
  private ProductDto productDto;

  @Override
  public Application configure() {
    MockitoAnnotations.initMocks(this);
    willThrow(new ClientNotFoundException()).given(submissionService)
        .getClientByIdInCrm(NON_EXISTING_CLIENT);
    willReturn(clientDto).given(submissionService).getClientByIdInCrm(EXISTING_CLIENT);
    willThrow(new ProductNotFoundException()).given(submissionService)
        .getProductByIdInCrm(NON_EXISTING_PRODUCT);
    willReturn(productDto).given(submissionService).getProductByIdInCrm(EXISTING_PRODUCT);

    return new ResourceConfig().register(new BillingResource(submissionService));
  }

  @Test
  public void givenExistingClientAndProductWhenCreateBillThenResponseStatusIsCreated() {
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
  public void givenNonExistingClientWhenCreateBillThenResponseStatusIsBadRequest() {
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(NON_EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, new ArrayList<>());
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request(MediaType.APPLICATION_JSON).post(requestEntity,
        Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenNonExistingProductWhenCreateBillThenResponseStatusIsBadRequest() {
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

}
