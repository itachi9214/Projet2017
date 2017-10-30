package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@Provider
public class SubmissionNotFoundExceptionMapper
    implements ExceptionMapper<SubmissionNotFoundException> {

  private ExceptionMapperResponse exceptionMapperResponse;

  public SubmissionNotFoundExceptionMapper() {
    this.exceptionMapperResponse = new ExceptionMapperResponse();
  }

  public SubmissionNotFoundExceptionMapper(ExceptionMapperResponse exceptionMapperResponse) {
    this.exceptionMapperResponse = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(SubmissionNotFoundException submissionNotFoundException) {
    return exceptionMapperResponse.createNotFoundExceptionMapper();
  }

}
