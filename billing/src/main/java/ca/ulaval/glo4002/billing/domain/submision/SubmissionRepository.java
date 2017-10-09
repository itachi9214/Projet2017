package ca.ulaval.glo4002.billing.domain.submision;

import ca.ulaval.glo4002.billing.domain.id.Id;

public interface SubmissionRepository {

  public void createSubmission(Submission submission);

  public Submission findSubmissionById(Id submissionNumber);

}
