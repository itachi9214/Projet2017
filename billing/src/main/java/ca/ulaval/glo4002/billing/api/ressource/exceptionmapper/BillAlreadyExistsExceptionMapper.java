package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@Provider
public class BillAlreadyExistsExceptionMapper extends BadRequestExceptionMapper
    implements ExceptionMapper<BillAlreadyExistsException> {

  @Override
  public Response toResponse(BillAlreadyExistsException billAlreadyExistsException) {
    return CreateBadRequestExceptionMapper("wrong status", "Invoice already accepted", "invoice");
  }

}
