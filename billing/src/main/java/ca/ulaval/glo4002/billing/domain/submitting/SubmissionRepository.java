package ca.ulaval.glo4002.billing.domain.submitting;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingNotFoundException;

public interface SubmissionRepository {

  public void createSubmission(Submission submission);

  public void deleteSubmission(Submission submission);

  public Submission findSubmissionById(Identity submissionNumber)
      throws SubmittingNotFoundException;

}
