package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.api.dto.client.ErrorDto;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;

@Provider
public class NegativeParameterExceptionMapper
    implements ExceptionMapper<NegativeParameterException> {

  @Override
  public Response toResponse(NegativeParameterException negativeParameterException) {
    ErrorDto errorDto = new ErrorDto("bad parameter",
        negativeParameterException.getParameter() + " cannot be negative",
        negativeParameterException.getParameter());
    return Response.status(Status.BAD_REQUEST).entity(errorDto).type(MediaType.APPLICATION_JSON)
        .build();
  }

}
