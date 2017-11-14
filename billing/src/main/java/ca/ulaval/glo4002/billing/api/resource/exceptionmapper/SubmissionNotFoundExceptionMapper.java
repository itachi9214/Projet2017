package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@Provider
public class SubmissionNotFoundExceptionMapper
    implements ExceptionMapper<SubmissionNotFoundException> {

  private ExceptionMapperFactory exceptionMapperFactory;

  public SubmissionNotFoundExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public SubmissionNotFoundExceptionMapper(ExceptionMapperFactory exceptionMapperFactory) {
    this.exceptionMapperFactory = exceptionMapperFactory;
  }

  @Override
  public Response toResponse(SubmissionNotFoundException submissionNotFoundException) {
    return exceptionMapperFactory.createNotFoundExceptionMapper();
  }

}
