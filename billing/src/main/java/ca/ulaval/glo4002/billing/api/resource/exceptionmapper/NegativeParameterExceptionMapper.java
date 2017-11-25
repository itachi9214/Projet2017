package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;

@Provider
public class NegativeParameterExceptionMapper
    implements ExceptionMapper<NegativeParameterException> {

  private static final String DESCRIPTION = " cannot be negative";
  private static final String ERROR = "bad parameter";
  private ExceptionMapperFactory exceptionMapperFactory;

  public NegativeParameterExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public NegativeParameterExceptionMapper(ExceptionMapperFactory exceptionMapperFactory) {
    this.exceptionMapperFactory = exceptionMapperFactory;
  }

  @Override
  public Response toResponse(NegativeParameterException negativeParameterException) {
    return exceptionMapperFactory.createBadRequestExceptionMapper(ERROR,
        negativeParameterException.getParameter() + DESCRIPTION,
        negativeParameterException.getParameter());
  }

}
