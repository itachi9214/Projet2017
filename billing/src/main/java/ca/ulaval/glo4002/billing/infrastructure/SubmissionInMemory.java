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
  public boolean getSubmissionById(Id submissionNumber) {
    if (submissions.isEmpty() || !submissions.containsKey(submissionNumber)) {
      return false;
    }
    return true;
  }

  @Override
  public Submission findSubmission(Id submissionNumber) {
    return submissions.get(submissionNumber);
  }

}
