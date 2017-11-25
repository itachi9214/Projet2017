package ca.ulaval.glo4002.billing.service.submitting;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.submitting.RequestSubmittingDto;
import ca.ulaval.glo4002.billing.api.dto.submitting.ResponseSubmittingDto;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;

public class SubmittingAssembler {

  private static final String BASE_URL = "/bills/";

  private IdentityFactory identityFactory;

  public SubmittingAssembler() {
    this.identityFactory = ServiceLocator.getService(IdentityFactory.class);
  }

  public SubmittingAssembler(IdentityFactory identityFactory) {
    this.identityFactory = identityFactory;
  }

  public ResponseSubmittingDto createResponseSubmissionDto(Submission submission) {
    ResponseSubmittingDto responseSubmissionDto = new ResponseSubmittingDto(
        submission.getBillNumber().getNumber(), submission.getTotalPrice(), submission.getDueTerm(),
        BASE_URL + submission.getBillNumber());
    return responseSubmissionDto;
  }

  public Submission createSubmission(RequestSubmittingDto requestSubmissionDto)
      throws NegativeParameterException {
    Submission submission = new Submission(identityFactory.createId(),
        requestSubmissionDto.getDueTerm(), requestSubmissionDto.getClientId(),
        requestSubmissionDto.getItems());
    return submission;
  }

}
