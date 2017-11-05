package ca.ulaval.glo4002.payment.api.resource.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

@Provider
public class BillNotFoundExceptionMapper implements ExceptionMapper<BillNotFoundException> {

  private ExceptionMapperFactory exceptionMapperFactory;

  public BillNotFoundExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public BillNotFoundExceptionMapper(ExceptionMapperFactory exceptionMapperResponse) {
    this.exceptionMapperFactory = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(BillNotFoundException billNotFoundException) {
    return exceptionMapperFactory.createNotFoundExceptionMapper();
  }

}
