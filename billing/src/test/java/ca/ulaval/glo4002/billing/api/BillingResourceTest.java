package ca.ulaval.glo4002.billing.api;

import static org.junit.Assert.assertEquals;

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
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;

@RunWith(MockitoJUnitRunner.class)
public class BillingResourceTest extends JerseyTest {

  private static final Long EXISTING_CLIENT = 2L;
  private static final Long NON_EXISTING_CLIENT = -6L;
  private static final int NON_EXISTING_PRODUCT = -4;
  private static final float A_PRICE = 15;
  private static final String A_NOTE = "note";
  private static final int A_QUANTITY = 2;

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(BillingResource.class);
  }

  @Test
  public void givenExistingClientWhenCreateBillThenResponseStatusIsCreated() {
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto();
    requestSubmissionDto.setClientId(EXISTING_CLIENT);
    requestSubmissionDto.setCreationDate(new Date());
    requestSubmissionDto.setDueTerm(DueTerm.DAYS30);
    requestSubmissionDto.setItems(new ArrayList<>());
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request().post(requestEntity, Response.class);

    assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenNonExistingClientWhenCreateBillThenResponseStatusIsBadRequest() {
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto();
    requestSubmissionDto.setClientId(NON_EXISTING_CLIENT);
    requestSubmissionDto.setCreationDate(new Date());
    requestSubmissionDto.setDueTerm(DueTerm.DAYS30);
    requestSubmissionDto.setItems(new ArrayList<>());
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request().post(requestEntity, Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenNonExistingProductWhenCreateBillThenResponseStatusIsBadRequest() {
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto();
    requestSubmissionDto.setClientId(EXISTING_CLIENT);
    requestSubmissionDto.setCreationDate(new Date());
    requestSubmissionDto.setDueTerm(DueTerm.DAYS30);
    List<OrderedProduct> items = new ArrayList<>();
    items.add(new OrderedProduct(NON_EXISTING_PRODUCT, A_PRICE, A_NOTE, A_QUANTITY));
    requestSubmissionDto.setItems(items);
    Entity<RequestSubmissionDto> requestEntity = Entity.entity(requestSubmissionDto,
        MediaType.APPLICATION_JSON);

    Response response = target("/bills").request().post(requestEntity, Response.class);

    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

}
