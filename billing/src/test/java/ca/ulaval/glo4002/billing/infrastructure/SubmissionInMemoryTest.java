package ca.ulaval.glo4002.billing.infrastructure;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.Submission.Submission;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionInMemoryTest {

  public SubmissionInMemory submissionInMemory;

  @Mock
  Submission submission;

  @Before
  public void setUp() {
    submissionInMemory = new SubmissionInMemory();
  }

  @Test
  public void givenSubmissionWhenCreateSubmissionPutSubmission() {
    submissionInMemory.createSubmission(submission);

    verify(submission).getClientId();
  }

}