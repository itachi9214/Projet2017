package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;

@RunWith(MockitoJUnitRunner.class)
public class NegativeParameterExceptionMapperTest {

  private static final String A_PARAMETER = "sample parameter";

  private NegativeParameterException negativeParameterException;
  private NegativeParameterExceptionMapper negativeParameterExceptionMapper;

  @Mock
  private ExceptionMapperResponse exceptionMapperResponse;

  @Before
  public void setUp() {
    negativeParameterExceptionMapper = new NegativeParameterExceptionMapper(
        exceptionMapperResponse);
    negativeParameterException = new NegativeParameterException(A_PARAMETER);
  }

  @Test
  public void givenNegativeParameterExceptioWhenToResponseThenStatusCodeIsBadRequest() {
    negativeParameterExceptionMapper.toResponse(negativeParameterException);

    verify(exceptionMapperResponse).createBadRequestExceptionMapper(anyString(), anyString(),
        anyString());
  }

}
