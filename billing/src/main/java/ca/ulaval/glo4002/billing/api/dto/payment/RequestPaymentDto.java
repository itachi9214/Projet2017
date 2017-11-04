package ca.ulaval.glo4002.billing.api.dto.payment;

import ca.ulaval.glo4002.billing.domain.payment.PaymentMethod;

public class RequestPaymentDto {
  private Long clientId;
  private float amount;
  private PaymentMethod paymentMethod;

  public Long getClientId() {
    return clientId;
  }

  public RequestPaymentDto() {
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

}
