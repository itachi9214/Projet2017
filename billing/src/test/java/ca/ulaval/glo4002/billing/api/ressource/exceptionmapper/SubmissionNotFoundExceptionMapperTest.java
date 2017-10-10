package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

public class SubmissionNotFoundExceptionMapperTest {

  private SubmissionNotFoundException submissionNotFoundException;
  private SubmissionNotFoundExceptionMapper submissionNotFoundExceptionMapper;

  @Before
  public void setUp() {
    submissionNotFoundExceptionMapper = new SubmissionNotFoundExceptionMapper();
    submissionNotFoundException = new SubmissionNotFoundException();
  }

  @Test
  public void givenNegativeParameterExceptioWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = submissionNotFoundExceptionMapper.toResponse(submissionNotFoundException);

    assertEquals(codeError.getStatus(), Status.NOT_FOUND.getStatusCode());
  }

}
