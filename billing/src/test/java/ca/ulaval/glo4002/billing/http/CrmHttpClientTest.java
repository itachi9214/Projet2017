package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;

@RunWith(MockitoJUnitRunner.class)
public class CrmHttpClientTest {

  private static final int AN_INT = 13;
  private static final String A_STRING = "name";
  private static final Long NON_EXISTING_CLIENT_NUMBER = -3L;
  private static final Long EXISTING_CLIENT_NUMBER = 1L;
  private static final Integer NON_EXISTING_PRODUCT_ID = -6;
  private static final Integer EXISTING_PRODUCT_ID = 3;

  private CrmHttpClient crmHttpClient;
  private ClientDto clientDto;
  private ProductDto productDto;
  private ObjectMapper mapper;

  @Mock
  private Response response;

  @Before
  public void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    clientDto = new ClientDto(EXISTING_CLIENT_NUMBER, Instant.now(), DueTerm.IMMEDIATE, A_STRING);
    productDto = new ProductDto(EXISTING_PRODUCT_ID, A_STRING, new BigDecimal(AN_INT));

    crmHttpClient = new CrmHttpClient() {
      protected Response callUrlWithGetMethod(String url) {
        return response;
      }
    };
  }

  @Test(expected = ClientNotFoundException.class)
  public void givenClientNumberNotFoundWhenGetClientDtoThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpClient.getClientDto(NON_EXISTING_CLIENT_NUMBER);
  }

  @Test
  public void givenClientNumberFoundWhenGetClientDtoThenReturnDto() throws IOException {
    willReturn(Status.OK.getStatusCode()).given(response).getStatus();
    willReturn(mapper.writeValueAsString(clientDto)).given(response).readEntity(String.class);

    ClientDto result = crmHttpClient.getClientDto(EXISTING_CLIENT_NUMBER);

    assertTrue(result instanceof ClientDto);
  }

  @Test(expected = ProductNotFoundException.class)
  public void givenProductIdNotFoundWhenGetProductDtoThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpClient.getProductDto(NON_EXISTING_PRODUCT_ID);
  }

  @Test
  public void givenProductIdFoundWhenGetProductDtoThenReturnDto() throws IOException {
    willReturn(Status.OK.getStatusCode()).given(response).getStatus();
    willReturn(mapper.writeValueAsString(productDto)).given(response).readEntity(String.class);

    ProductDto result = crmHttpClient.getProductDto(EXISTING_PRODUCT_ID);

    assertTrue(result instanceof ProductDto);
  }

}
