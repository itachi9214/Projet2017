package ca.ulaval.glo4002.billing.service.submission;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

public class SubmissionAssembler {

  private static final String BILLS = "/bills/";

  private IdentityFactory identityFactory;

  public SubmissionAssembler() {
    identityFactory = new IdentityFactory();
  }

  public ResponseSubmissionDto createResponseSubmissionDto(Submission submission) {
    ResponseSubmissionDto responseSubmissionDto = new ResponseSubmissionDto(
        submission.getBillNumber().getNumber(), submission.getTotalPrice(), submission.getDueTerm(),
        BILLS + submission.getBillNumber());
    return responseSubmissionDto;
  }

  public Submission createSubmission(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    Submission submission = new Submission(identityFactory.createAndGenerateId(),
        requestSubmissionDto.getDueTerm(), requestSubmissionDto.getClientId(),
        requestSubmissionDto.getItems());
    return submission;
  }

}
