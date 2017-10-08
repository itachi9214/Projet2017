package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class ProductNotFoundExceptionMapperTest {
  ProductNotFoundExceptionMapper productNotFoundExceptionMapper;
  ProductNotFoundException productNotFoundException;
  private static int idProduct = 1;

  @Before
  public void setUp() {
    productNotFoundExceptionMapper = new ProductNotFoundExceptionMapper();
    productNotFoundException = new ProductNotFoundException(idProduct);
  }

  @Test
  public void givenProductNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = productNotFoundExceptionMapper.toResponse(productNotFoundException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
