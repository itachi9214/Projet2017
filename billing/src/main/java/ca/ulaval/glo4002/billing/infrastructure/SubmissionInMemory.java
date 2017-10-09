package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.Submission.Id;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;

public class SubmissionInMemory implements SubmissionRepository {

  private Map<Id, Submission> submissions = new HashMap<>();

  @Override
  public void createSubmission(Submission submission) {
    submissions.put(submission.getBillNumber(), submission);
  }

  @Override
  public Submission findSubmissionById(Id submissionNumber) {
    if (!submissions.containsKey(submissionNumber) || submissions.isEmpty()) {
      throw new SubmissionNotFoundException();
    }
    return submissions.get(submissionNumber);
  }

}
