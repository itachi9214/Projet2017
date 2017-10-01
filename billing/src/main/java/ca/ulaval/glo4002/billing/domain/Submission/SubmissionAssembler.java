package ca.ulaval.glo4002.billing.domain.Submission;

import java.util.UUID;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;

public class SubmissionAssembler {

  public ResponseSubmissionDto create(Submission submission) {
    ResponseSubmissionDto responseSubmissionDto = new ResponseSubmissionDto(submission.getBillNumber(), submission.getTotalPrice(),
        submission.getDueTerm(), "/bills/" + submission.getBillNumber());
    return responseSubmissionDto;
  }

  public Submission create(RequestSubmissionDto requestSubmissionDto) {
    Submission submission = new Submission(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
        requestSubmissionDto.getDueTerm(), requestSubmissionDto.getClientId(), requestSubmissionDto.getItems());
    submission.calculatePrice();
    return submission;
  }

}
