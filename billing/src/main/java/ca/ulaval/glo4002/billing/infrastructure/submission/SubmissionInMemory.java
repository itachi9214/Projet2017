package ca.ulaval.glo4002.billing.infrastructure.submission;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

public class SubmissionInMemory implements SubmissionRepository {

  private Map<Identity, Submission> submissions = new HashMap<>();

  public SubmissionInMemory() {
  }

  @Override
  public void createSubmission(Submission submission) {
    submissions.put(submission.getBillNumber(), submission);
  }

  @Override
  public Submission findSubmissionById(Identity submissionNumber) {
    if (!submissions.containsKey(submissionNumber)) {
      throw new SubmissionNotFoundException();
    }
    return submissions.get(submissionNumber);
  }

}
