package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.api.dto.product.ProductErrorDto;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

  @Override
  public Response toResponse(ProductNotFoundException productNotFoundException) {
    ProductErrorDto productErrorDto = new ProductErrorDto("not found",
        "product " + productNotFoundException.getProductId() + " not found", "product");
    return Response.status(Status.BAD_REQUEST).entity(productErrorDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
