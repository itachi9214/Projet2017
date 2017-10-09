package ca.ulaval.glo4002.billing.service.submission;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.id.IdFactory;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

public class SubmissionAssembler {

  private IdFactory idFactory;

  public SubmissionAssembler() {
    idFactory = new IdFactory();
  }

  public ResponseSubmissionDto createResponseSubmissionDto(Submission submission) {
    ResponseSubmissionDto responseSubmissionDto = new ResponseSubmissionDto(
        submission.getBillNumber().getNumber(), submission.getTotalPrice(), submission.getDueTerm(),
        "/bills/" + submission.getBillNumber());
    return responseSubmissionDto;
  }

  public Submission createSubmission(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    Submission submission = new Submission(idFactory.createAndGenerateId(),
        requestSubmissionDto.getDueTerm(), requestSubmissionDto.getClientId(),
        requestSubmissionDto.getItems());
    return submission;
  }

}
