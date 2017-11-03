package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@Provider
public class BillAlreadyExistsExceptionMapper
    implements ExceptionMapper<BillAlreadyExistsException> {

  private static final String ENTITY = "invoice";
  private static final String DESCRIPTION = "Invoice already accepted";
  private static final String ERROR = "wrong status";

  private ExceptionMapperFactory exceptionMapperFactory;

  public BillAlreadyExistsExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public BillAlreadyExistsExceptionMapper(ExceptionMapperFactory exceptionMapperResponse) {
    this.exceptionMapperFactory = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(BillAlreadyExistsException billAlreadyExistsException) {
    return exceptionMapperFactory.createBadRequestExceptionMapper(ERROR, DESCRIPTION, ENTITY);
  }

}
