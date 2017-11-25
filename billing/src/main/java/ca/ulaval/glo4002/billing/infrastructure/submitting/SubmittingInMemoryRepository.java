package ca.ulaval.glo4002.billing.infrastructure.submitting;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;

public class SubmittingInMemoryRepository implements SubmissionRepository {

  private Map<Identity, Submission> submissions = new HashMap<>();

  public SubmittingInMemoryRepository() {
  }

  @Override
  public void createSubmission(Submission submission) {
    submissions.put(submission.getBillNumber(), submission);
  }

  @Override
  public Submission findSubmissionById(Identity submissionNumber)
      throws SubmittingNotFoundException {
    if (!submissions.containsKey(submissionNumber)) {
      throw new SubmittingNotFoundException();
    }
    return submissions.get(submissionNumber);
  }

  @Override
  public void deleteSubmission(Submission submission) {
    submissions.remove(submission.getBillNumber());
  }

}
