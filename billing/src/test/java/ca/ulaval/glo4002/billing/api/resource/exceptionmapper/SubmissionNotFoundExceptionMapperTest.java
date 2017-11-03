package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.resource.exceptionmapper.ExceptionMapperFactory;
import ca.ulaval.glo4002.billing.api.resource.exceptionmapper.SubmissionNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionNotFoundExceptionMapperTest {

  private SubmissionNotFoundException submissionNotFoundException;
  private SubmissionNotFoundExceptionMapper submissionNotFoundExceptionMapper;

  @Mock
  private ExceptionMapperFactory exceptionMapperResponse;

  @Before
  public void setUp() {
    submissionNotFoundExceptionMapper = new SubmissionNotFoundExceptionMapper(
        exceptionMapperResponse);
    submissionNotFoundException = new SubmissionNotFoundException();
  }

  @Test
  public void givenNegativeParameterExceptioWhenToResponseThenStatusCodeIsBadRequest() {
    submissionNotFoundExceptionMapper.toResponse(submissionNotFoundException);

    verify(exceptionMapperResponse).createNotFoundExceptionMapper();
  }

}
