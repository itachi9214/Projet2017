package ca.ulaval.glo4002.payment.domain.payment;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import ca.ulaval.glo4002.payment.domain.identity.Identity;

@Entity
public class Payment {

  @EmbeddedId
  protected Identity paymentNumber;
  protected float amount;
  protected long clientId;
  @Embedded
  protected PaymentMethod paymentMethod;

  public Payment(Identity paymentNumber, float amount, long clientId, PaymentMethod paymentMethod) {
    this.paymentNumber = paymentNumber;
    this.amount = amount;
    this.clientId = clientId;
    this.paymentMethod = paymentMethod;
  }

  public float getAmount() {
    return amount;
  }

  public long getClientId() {
    return clientId;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Identity getPaymentNumber() {
    return paymentNumber;
  }

  public void setPaymentNumber(Identity paymentNumber) {
    this.paymentNumber = paymentNumber;
  }

}
