package ca.ulaval.glo4002.payment.http;

import ca.ulaval.glo4002.payment.domain.payment.PaymentException;

public class ClientNotFoundException extends PaymentException {

  private static final long serialVersionUID = 1L;
  private Long clientId;

  public ClientNotFoundException(Long clientId) {
    this.clientId = clientId;
  }

  public Long getClientId() {
    return clientId;
  }

}
