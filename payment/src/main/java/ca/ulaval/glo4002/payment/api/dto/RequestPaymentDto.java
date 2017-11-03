package ca.ulaval.glo4002.payment.api.dto;

import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;

public class RequestPaymentDto {

  private Long clientId;
  private float amount;
  private PaymentMethod paymentMethod;

  public RequestPaymentDto() {
  }

  public RequestPaymentDto(Long clientId, float amount, PaymentMethod paymentMethod) {
    this.clientId = clientId;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
  }

  public Long getClientId() {
    return clientId;
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
