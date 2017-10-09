package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.api.dto.submission.EmptyBodyErrorDto;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@Provider
public class SubmissionNotFoundExceptionMapper
    implements ExceptionMapper<SubmissionNotFoundException> {

  @Override
  public Response toResponse(SubmissionNotFoundException submissionNotFoundException) {
    EmptyBodyErrorDto emptyBodyErrorDto = new EmptyBodyErrorDto();
    return Response.status(Status.NOT_FOUND).entity(emptyBodyErrorDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
