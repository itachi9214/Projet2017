package ca.ulaval.glo4002.payment.domain.payment;

public interface PaymentRepository {

  public void savePayment(Payment payment);

  public Payment findPaymentById(long id);

}
