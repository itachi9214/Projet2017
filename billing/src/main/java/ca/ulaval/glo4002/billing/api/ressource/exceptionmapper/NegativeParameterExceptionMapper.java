package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;

@Provider
public class NegativeParameterExceptionMapper extends ExceptionMapperResponse
    implements ExceptionMapper<NegativeParameterException> {

  @Override
  public Response toResponse(NegativeParameterException negativeParameterException) {
    return createBadRequestExceptionMapper("bad parameter",
        negativeParameterException.getParameter() + " cannot be negative",
        negativeParameterException.getParameter());
  }

}
