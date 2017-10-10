package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@Provider
public class SubmissionNotFoundExceptionMapper extends ExceptionMapperResponse
    implements ExceptionMapper<SubmissionNotFoundException> {

  @Override
  public Response toResponse(SubmissionNotFoundException submissionNotFoundException) {
    return createNotFoundExceptionMapper();
  }

}
