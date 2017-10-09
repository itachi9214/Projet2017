package ca.ulaval.glo4002.billing.infrastructure.submission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.id.Id;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionInMemory;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionInMemoryTest {

  private static final Id EXISTING_SUBMISSION_NUMBER = new Id(100L);
  private static final Id NOT_EXISTING_SUBMISSION_NUMBER = new Id(200L);
  private SubmissionRepository submissionInMemory;
  private Map<Id, Submission> submissions;

  @Mock
  Submission submission;

  @Before
  public void setUp() {
    submissions = new HashMap<>();
    submissionInMemory = new SubmissionInMemory(submissions);
    when(submission.getBillNumber()).thenReturn(EXISTING_SUBMISSION_NUMBER);
  }

  @Test
  public void givenSubmissionWhenCreateSubmissionThenSubmissionsContainsSubmission() {
    submissionInMemory.createSubmission(submission);

    assertEquals(submissions.containsKey(EXISTING_SUBMISSION_NUMBER), true);
  }

  @Test
  public void givenExistingSubmissionNumberWhenFindSubmissionThenGetSubmissionNumber() {
    submissionInMemory.createSubmission(submission);

    assertEquals(submissionInMemory.findSubmissionById(EXISTING_SUBMISSION_NUMBER), submission);
  }

  @Test(expected = SubmissionNotFoundException.class)
  public void givenExistingSubmissionNumberWhenNotFindSubmissionThenThrowException() {
    submissionInMemory.findSubmissionById(NOT_EXISTING_SUBMISSION_NUMBER);
  }

}
