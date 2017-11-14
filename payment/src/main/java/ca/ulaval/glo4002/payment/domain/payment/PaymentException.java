package ca.ulaval.glo4002.payment.domain.payment;

public class PaymentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PaymentException() {
  }

  public PaymentException(String message) {
    super(message);
  }

}
