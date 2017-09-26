package ca.ulaval.glo4002.billing.exception;

public class NotFoundClientException extends BillingException {

  private static final long serialVersionUID = 1L;

  public NotFoundClientException() {
  }

  public NotFoundClientException(String message) {
    super(message);
  }
}
