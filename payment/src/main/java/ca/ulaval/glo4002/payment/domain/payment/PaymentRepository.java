package ca.ulaval.glo4002.payment.domain.payment;

import ca.ulaval.glo4002.payment.domain.identity.Identity;
import ca.ulaval.glo4002.payment.infrastructure.payment.PaymentNotFoundException;

public interface PaymentRepository {

  public void savePayment(Payment payment);

  public Payment findPaymentById(Identity id) throws PaymentNotFoundException;

}
