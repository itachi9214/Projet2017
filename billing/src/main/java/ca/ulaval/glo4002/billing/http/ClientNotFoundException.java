package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.domain.bill.BillingException;

public class ClientNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;

  public ClientNotFoundException() {
  }

  public ClientNotFoundException(String message) {
    super(message);
  }

}
