package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;

public class SubmissionInMemory implements SubmissionRepository {

  private Map<Long, Submission> submissions = new HashMap<>();

  @Override
  public void createSubmission(Submission submission) {
    submissions.put(submission.getClientId(), submission);
  }

}
