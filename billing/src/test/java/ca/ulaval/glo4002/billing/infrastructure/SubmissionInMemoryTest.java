package ca.ulaval.glo4002.billing.infrastructure;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.Submission.Submission;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionInMemoryTest {

  private static final Long EXISTING_SUBMISSION_NUMBER = 100L;
  private static final Long NOT_EXISTING_SUBMISSION_NUMBER = 200L;

  private SubmissionInMemory submissionInMemory;

  @Mock
  Submission submissionMock;

  @Before
  public void setUp() {
    submissionInMemory = new SubmissionInMemory();
    when(submissionMock.getBillNumber()).thenReturn(EXISTING_SUBMISSION_NUMBER);
  }

  @Test
  public void givenExistingSubmissionNumberWhenCreateSubmissionAndGetSubmissionByIdThenReturnSubmission() {
    submissionInMemory.createSubmission(submissionMock);

    Submission submission = submissionInMemory.getSubmissionById(EXISTING_SUBMISSION_NUMBER);

    assertEquals(submission, submissionMock);
  }

  @Test(expected = SubmissionNotFoundException.class)
  public void givenNotExistingSubmissionNumberWhenGetSubmissionBySubmissionNUmberThenThrowException() {
    submissionInMemory.getSubmissionById(NOT_EXISTING_SUBMISSION_NUMBER);
  }

}