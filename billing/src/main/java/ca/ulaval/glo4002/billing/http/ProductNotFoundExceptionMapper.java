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
    ProductErrorDto productErrorDto = new ProductErrorDto("Product", "Product Not Found",
        "Product");
    return Response.status(Status.NOT_FOUND).entity(productErrorDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
