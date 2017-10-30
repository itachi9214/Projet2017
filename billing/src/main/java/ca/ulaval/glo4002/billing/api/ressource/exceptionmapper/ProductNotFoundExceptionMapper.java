package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

  private ExceptionMapperResponse exceptionMapperResponse;

  public ProductNotFoundExceptionMapper() {
    this.exceptionMapperResponse = new ExceptionMapperResponse();
  }

  public ProductNotFoundExceptionMapper(ExceptionMapperResponse exceptionMapperResponse) {
    this.exceptionMapperResponse = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(ProductNotFoundException productNotFoundException) {
    return exceptionMapperResponse.createBadRequestExceptionMapper("not found",
        "product " + productNotFoundException.getProductId() + " not found", "product");
  }

}
