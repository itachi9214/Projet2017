package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;

public class NegativeParameterExceptionMapperTest {

  private static final String A_PARAMETER = "sample parameter";

  private NegativeParameterException negativeParameterException;
  private NegativeParameterExceptionMapper negativeParameterExceptionMapper;

  @Before
  public void setUp() {
    negativeParameterExceptionMapper = new NegativeParameterExceptionMapper();
    negativeParameterException = new NegativeParameterException(A_PARAMETER);
  }

  @Test
  public void givenNegativeParameterExceptioWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = negativeParameterExceptionMapper.toResponse(negativeParameterException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
