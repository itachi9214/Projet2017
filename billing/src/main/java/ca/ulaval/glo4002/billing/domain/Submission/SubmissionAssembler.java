package ca.ulaval.glo4002.billing.domain.Submission;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;

public class SubmissionAssembler {

  public ResponseSubmissionDto createResponseSubmissionDto(Submission submission) {
    ResponseSubmissionDto responseSubmissionDto = new ResponseSubmissionDto(
        submission.getBillNumber(), submission.getTotalPrice(), submission.getDueTerm(),
        "/bills/" + submission.getBillNumber());
    return responseSubmissionDto;
  }

  public Submission createSubmission(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    Submission submission = new Submission(requestSubmissionDto.getDueTerm(),
        requestSubmissionDto.getClientId(), requestSubmissionDto.getItems());
    return submission;
  }

}
