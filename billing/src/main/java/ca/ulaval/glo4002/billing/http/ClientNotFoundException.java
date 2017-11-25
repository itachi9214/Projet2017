package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.domain.submitting.BillingException;

public class ClientNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;
  private Long clientId;

  public ClientNotFoundException(Long clientId) {
    this.clientId = clientId;
  }

  public Long getClientId() {
    return clientId;
  }

}
