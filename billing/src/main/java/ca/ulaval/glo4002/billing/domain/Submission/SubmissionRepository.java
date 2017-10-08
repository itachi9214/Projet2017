package ca.ulaval.glo4002.billing.domain.Submission;

public interface SubmissionRepository {

  public void createSubmission(Submission submission);

  public boolean getSubmissionById(Id submissionNumber);

  public Submission findSubmission(Id submissionNumber);

}
