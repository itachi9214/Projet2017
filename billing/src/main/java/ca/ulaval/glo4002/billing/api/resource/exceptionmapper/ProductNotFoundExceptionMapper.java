package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

  private static final String ENTITY = "product ";
  private static final String ERROR = " not found";

  private ExceptionMapperFactory exceptionMapperFactory;

  public ProductNotFoundExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public ProductNotFoundExceptionMapper(ExceptionMapperFactory exceptionMapperFactory) {
    this.exceptionMapperFactory = exceptionMapperFactory;
  }

  @Override
  public Response toResponse(ProductNotFoundException productNotFoundException) {
    return exceptionMapperFactory.createBadRequestExceptionMapper(ERROR,
        ENTITY + productNotFoundException.getProductId() + ERROR, ENTITY);
  }

}
