package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class ProductNotFoundExceptionMapperTest {

  private static final int ID_PRODUCT = 1;
  private ProductNotFoundExceptionMapper productNotFoundExceptionMapper;
  private ProductNotFoundException productNotFoundException;

  @Before
  public void setUp() {
    productNotFoundExceptionMapper = new ProductNotFoundExceptionMapper();
    productNotFoundException = new ProductNotFoundException(ID_PRODUCT);
  }

  @Test
  public void givenProductNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = productNotFoundExceptionMapper.toResponse(productNotFoundException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
