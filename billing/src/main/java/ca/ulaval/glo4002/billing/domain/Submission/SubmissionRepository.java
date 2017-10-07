package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.bill.RequestBillDto;

public interface SubmissionRepository {

  public void createSubmission(Submission submission);

  public boolean getSubmissionById(Long submissionNumber);

  public Submission findSubmission(RequestBillDto requestBillDto);

}
