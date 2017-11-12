package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.math.BigDecimal;

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

import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;

@RunWith(MockitoJUnitRunner.class)
public class CrmHttpProductTest {

  private static final String A_STRING = "name";
  private static final int AN_INT = 13;
  private static final Integer NON_EXISTING_PRODUCT_ID = -6;
  private static final Integer EXISTING_PRODUCT_ID = 3;

  private ProductDto productDto;
  private ObjectMapper mapper;
  private CrmHttpProduct crmHttpProduct;

  @Mock
  private UtilHttp utilHttp;
  @Mock
  private Response response;

  @Before
  public void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    productDto = new ProductDto(EXISTING_PRODUCT_ID, A_STRING, new BigDecimal(AN_INT));
    willReturn(response).given(utilHttp).callUrlWithGetMethod(anyString());
    crmHttpProduct = new CrmHttpProduct(utilHttp);
  }

  @Test(expected = ProductNotFoundException.class)
  public void givenProductIdNotFoundWhenGetProductDtoThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpProduct.getProductDto(NON_EXISTING_PRODUCT_ID);
  }

  @Test
  public void whenGetProductDtoThenReturnProductDto() throws IOException {
    willReturn(Status.OK.getStatusCode()).given(response).getStatus();
    willReturn(mapper.writeValueAsString(productDto)).given(response).readEntity(String.class);

    ProductDto result = crmHttpProduct.getProductDto(EXISTING_PRODUCT_ID);

    assertTrue(result instanceof ProductDto);
  }

}