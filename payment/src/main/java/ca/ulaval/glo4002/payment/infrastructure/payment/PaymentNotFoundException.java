package ca.ulaval.glo4002.payment.infrastructure.payment;

import ca.ulaval.glo4002.payment.domain.payment.PaymentException;

public class PaymentNotFoundException extends PaymentException {

  private static final long serialVersionUID = 1L;

  public PaymentNotFoundException() {
    super();
  }

}
