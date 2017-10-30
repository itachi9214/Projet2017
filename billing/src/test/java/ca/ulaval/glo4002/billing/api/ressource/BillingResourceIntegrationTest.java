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

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;
import ca.ulaval.glo4002.billing.service.bill.BillService;
import ca.ulaval.glo4002.billing.service.submission.SubmissionService;

@RunWith(MockitoJUnitRunner.class)
public class BillingResourceIntegrationTest extends JerseyTest {

  private static final Long EXISTING_CLIENT = 2L;
  private static final Long NON_EXISTING_CLIENT = -6L;
  private static final int NON_EXISTING_PRODUCT = -4;
  private static final int EXISTING_PRODUCT = 1;
  private static final float A_PRICE = 15;
  private static final String A_NOTE = "note";
  private static final int A_QUANTITY = 2;

  private ClientDto clientDto;

  @Mock
  private SubmissionService submissionService;
  @Mock
  private BillService billService;

  @Override
  public Application configure() {
    MockitoAnnotations.initMocks(this);
    clientDto = new ClientDto();

    ServiceLocator.register(billService);
    ServiceLocator.register(submissionService);

    willThrow(new ClientNotFoundException(NON_EXISTING_CLIENT)).given(submissionService)
        .getAndVerifyClientExists(NON_EXISTING_CLIENT);
    willReturn(clientDto).given(submissionService).getAndVerifyClientExists(EXISTING_CLIENT);

    return new ResourceConfig().packages("ca.ulaval.glo4002.billing");
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

  @Ignore
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

  @Ignore
  @Test
  public void givenNonExistingProductWhenCreateSubmissionThenResponseStatusIsBadRequest()
      throws NegativeParameterException {
    List<OrderedProduct> items = new ArrayList<>();
    willThrow(new ProductNotFoundException(NON_EXISTING_PRODUCT)).given(submissionService)
        .verifyProductsExist(items);

    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, items);
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);
    willThrow(new ProductNotFoundException(NON_EXISTING_PRODUCT)).given(submissionService)
        .createSubmission(requestSubmissionDto);

    Response response = target("/bills").request(MediaType.APPLICATION_JSON).post(requestEntity,
        Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

}
