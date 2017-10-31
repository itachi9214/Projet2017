package ca.ulaval.glo4002.billing.infrastructure.submission;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionInMemoryRepositoryTest {

  private static final Identity SUBMISSION_NUMBER = new Identity(200L);

  private SubmissionRepository submissionInMemory;

  @Mock
  private Submission submission;

  @Before
  public void setUp() {
    submissionInMemory = new SubmissionInMemoryRepository();
    willReturn(SUBMISSION_NUMBER).given(submission).getBillNumber();
  }

  @Test
  public void givenSubmissionWhenCreateSubmissionThenFindSubmission() {
    submissionInMemory.createSubmission(submission);

    assertEquals(submissionInMemory.findSubmissionById(SUBMISSION_NUMBER), submission);
  }

  @Test(expected = SubmissionNotFoundException.class)
  public void givenNotExistingSubmissionNumberWhenFindSubmissionThenThrowException() {
    submissionInMemory.findSubmissionById(SUBMISSION_NUMBER);
  }

  @Test(expected = SubmissionNotFoundException.class)
  public void givenExistingSubmissionWhenDeleteSubmissionThenSubmissionCannotBeFound() {
    submissionInMemory.createSubmission(submission);

    submissionInMemory.deleteSubmission(submission);

    submissionInMemory.findSubmissionById(SUBMISSION_NUMBER);
  }

}
