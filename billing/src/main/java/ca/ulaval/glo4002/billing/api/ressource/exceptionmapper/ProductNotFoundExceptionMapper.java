package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

@Provider
public class ProductNotFoundExceptionMapper extends BadRequestExceptionMapper
    implements ExceptionMapper<ProductNotFoundException> {

  @Override
  public Response toResponse(ProductNotFoundException productNotFoundException) {
    return CreateBadRequestExceptionMapper("not found",
        "product " + productNotFoundException.getProductId() + " not found", "product");
  }

}
