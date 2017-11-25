package ca.ulaval.glo4002.billing.infrastructure.submitting;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingInMemoryRepository;
import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class SubmittingInMemoryRepositoryTest {

  private static final Identity SUBMISSION_NUMBER = new Identity(200L);

  private SubmissionRepository submissionInMemory;

  @Mock
  private Submission submission;

  @Before
  public void setUp() {
    submissionInMemory = new SubmittingInMemoryRepository();
    willReturn(SUBMISSION_NUMBER).given(submission).getBillNumber();
  }

  @Test
  public void givenSubmissionWhenCreateSubmissionThenFindTheSameSubmission() {
    submissionInMemory.createSubmission(submission);

    Submission submissionFound = submissionInMemory.findSubmissionById(SUBMISSION_NUMBER);
    assertEquals(submission, submissionFound);
  }

  @Test(expected = SubmittingNotFoundException.class)
  public void givenNoSubmissionWhenFindSubmissionThenThrowSubmissionNotFoundException() {
    submissionInMemory.findSubmissionById(SUBMISSION_NUMBER);
  }

  @Test(expected = SubmittingNotFoundException.class)
  public void givenExistingSubmissionWhenDeleteSubmissionThenThrowSubmissionNotFoundException() {
    submissionInMemory.createSubmission(submission);

    submissionInMemory.deleteSubmission(submission);

    submissionInMemory.findSubmissionById(SUBMISSION_NUMBER);
  }

}
