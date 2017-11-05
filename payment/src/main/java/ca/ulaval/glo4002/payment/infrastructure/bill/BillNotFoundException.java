package ca.ulaval.glo4002.payment.infrastructure.bill;

import ca.ulaval.glo4002.payment.domain.payment.PaymentException;

public class BillNotFoundException extends PaymentException {

  private static final long serialVersionUID = 1L;

  public BillNotFoundException() {
    super();
  }

}
