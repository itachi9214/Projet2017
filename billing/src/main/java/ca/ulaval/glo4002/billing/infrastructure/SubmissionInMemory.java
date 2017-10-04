package ca.ulaval.glo4002.billing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;

public class SubmissionInMemory implements SubmissionRepository {

  private static final String MESSAGE_ERROR_SUBMISSION = "Error : submission not found";
  private Map<Long, Submission> submissions = new HashMap<>();

  @Override
  public void createSubmission(Submission submission) {
    submissions.put(submission.getBillNumber(), submission);
  }

  @Override
  public Submission getSubmissionById(Long submissionNumber) {
    if (submissions.isEmpty() || !submissions.containsKey(submissionNumber)) {
      throw new SubmissionNotFoundException(MESSAGE_ERROR_SUBMISSION);
    }
    return submissions.get(submissionNumber);
  }

}
