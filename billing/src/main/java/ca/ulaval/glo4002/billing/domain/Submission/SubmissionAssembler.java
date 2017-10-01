package ca.ulaval.glo4002.billing.domain.Submission;

import java.util.UUID;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;

public class SubmissionAssembler {

  public ResponseSubmissionDto create(Submission submission) {
    ResponseSubmissionDto responseBillDto = new ResponseSubmissionDto(submission.getBillNumber(), submission.getBillTotal(),
        submission.getDueTerm(), "/bills/" + submission.getBillNumber());
    return responseBillDto;
  }

  public Submission create(RequestSubmissionDto requestSubmissionDto) {
    Submission bill = new Submission(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
        requestSubmissionDto.getDueTerm(), requestSubmissionDto.getClientId(), requestSubmissionDto.getItems());
    bill.calculatePrice();
    return bill;
  }

}
