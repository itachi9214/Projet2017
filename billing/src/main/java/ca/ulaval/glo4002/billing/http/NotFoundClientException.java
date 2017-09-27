package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.domain.bill.BillingException;

public class NotFoundClientException extends BillingException {

  private static final long serialVersionUID = 1L;

  public NotFoundClientException() {
  }

  public NotFoundClientException(String message) {
    super(message);
  }

}
