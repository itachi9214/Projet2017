package ca.ulaval.glo4002.billing.domain.submision;

public class BillingException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BillingException() {
  }

  public BillingException(String message) {
    super(message);
  }

}
