package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@Provider
public class BillAlreadyExistsExceptionMapper
    implements ExceptionMapper<BillAlreadyExistsException> {

  private ExceptionMapperResponse exceptionMapperResponse;

  public BillAlreadyExistsExceptionMapper(ExceptionMapperResponse exceptionMapperResponse) {
    this.exceptionMapperResponse = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(BillAlreadyExistsException billAlreadyExistsException) {
    return exceptionMapperResponse.createBadRequestExceptionMapper("wrong status",
        "Invoice already accepted", "invoice");
  }

}
