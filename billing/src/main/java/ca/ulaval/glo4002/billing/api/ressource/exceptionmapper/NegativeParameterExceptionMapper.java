package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;

@Provider
public class NegativeParameterExceptionMapper
    implements ExceptionMapper<NegativeParameterException> {

  private static final String DESCRIPTION = " cannot be negative";
  private static final String ERROR = "bad parameter";
  private ExceptionMapperResponse exceptionMapperResponse;

  public NegativeParameterExceptionMapper() {
    this.exceptionMapperResponse = new ExceptionMapperResponse();
  }

  public NegativeParameterExceptionMapper(ExceptionMapperResponse exceptionMapperResponse) {
    this.exceptionMapperResponse = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(NegativeParameterException negativeParameterException) {
    return exceptionMapperResponse.createBadRequestExceptionMapper(ERROR,
        negativeParameterException.getParameter() + DESCRIPTION,
        negativeParameterException.getParameter());
  }

}
