package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;

@RunWith(MockitoJUnitRunner.class)
public class NegativeParameterExceptionMapperTest {

  private static final String A_PARAMETER = "sample parameter";
  private static final String DESCRIPTION = " cannot be negative";
  private static final String ERROR = "bad parameter";

  private NegativeParameterException negativeParameterException;
  private NegativeParameterExceptionMapper negativeParameterExceptionMapper;

  @Mock
  private ExceptionMapperFactory exceptionMapperResponse;

  @Before
  public void setUp() {
    negativeParameterExceptionMapper = new NegativeParameterExceptionMapper(
        exceptionMapperResponse);
    negativeParameterException = new NegativeParameterException(A_PARAMETER);
  }

  @Test
  public void whenToResponseThenVerifyCreateBadResquestExceptionMapperIsCalled() {
    negativeParameterExceptionMapper.toResponse(negativeParameterException);

    verify(exceptionMapperResponse).createBadRequestExceptionMapper(ERROR,
        A_PARAMETER + DESCRIPTION, A_PARAMETER);
  }

}
