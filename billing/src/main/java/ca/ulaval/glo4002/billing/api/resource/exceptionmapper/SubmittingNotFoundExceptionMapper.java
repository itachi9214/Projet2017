package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingNotFoundException;

@Provider
public class SubmittingNotFoundExceptionMapper
    implements ExceptionMapper<SubmittingNotFoundException> {

  private ExceptionMapperFactory exceptionMapperFactory;

  public SubmittingNotFoundExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public SubmittingNotFoundExceptionMapper(ExceptionMapperFactory exceptionMapperFactory) {
    this.exceptionMapperFactory = exceptionMapperFactory;
  }

  @Override
  public Response toResponse(SubmittingNotFoundException submissionNotFoundException) {
    return exceptionMapperFactory.createNotFoundExceptionMapper();
  }

}
