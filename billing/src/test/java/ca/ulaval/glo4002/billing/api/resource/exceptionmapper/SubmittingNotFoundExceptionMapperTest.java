package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class SubmittingNotFoundExceptionMapperTest {

  private SubmittingNotFoundException submissionNotFoundException;
  private SubmittingNotFoundExceptionMapper submissionNotFoundExceptionMapper;

  @Mock
  private ExceptionMapperFactory exceptionMapperResponse;

  @Before
  public void setUp() {
    submissionNotFoundExceptionMapper = new SubmittingNotFoundExceptionMapper(
        exceptionMapperResponse);
    submissionNotFoundException = new SubmittingNotFoundException();
  }

  @Test
  public void whenToResponseThenVerifyCreateNotFoundExceptionMapperIsCalled() {
    submissionNotFoundExceptionMapper.toResponse(submissionNotFoundException);

    verify(exceptionMapperResponse).createNotFoundExceptionMapper();
  }

}
