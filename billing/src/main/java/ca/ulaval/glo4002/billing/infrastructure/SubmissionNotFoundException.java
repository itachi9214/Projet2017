package ca.ulaval.glo4002.billing.infrastructure;

import ca.ulaval.glo4002.billing.domain.Submission.BillingException;

public class SubmissionNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;

  public SubmissionNotFoundException() {
    super();
  }

  public SubmissionNotFoundException(String message) {
    super(message);
  }

}
