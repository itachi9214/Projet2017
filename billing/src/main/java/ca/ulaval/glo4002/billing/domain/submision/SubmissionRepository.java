package ca.ulaval.glo4002.billing.domain.submision;

import ca.ulaval.glo4002.billing.domain.identity.Identity;

public interface SubmissionRepository {

  public void createSubmission(Submission submission);

  public Submission findSubmissionById(Identity submissionNumber);

}
