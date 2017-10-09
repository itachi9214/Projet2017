package ca.ulaval.glo4002.billing.infrastructure.submission;

import ca.ulaval.glo4002.billing.domain.bill.BillingException;

public class SubmissionNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;

  public SubmissionNotFoundException() {
    super();
  }

  public SubmissionNotFoundException(String message) {
    super(message);
  }

}
