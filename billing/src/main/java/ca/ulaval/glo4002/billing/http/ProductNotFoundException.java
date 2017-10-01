package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.domain.Submission.BillingException;

public class ProductNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;

  public ProductNotFoundException() {
  }

  public ProductNotFoundException(String message) {
    super(message);
  }

}
