package ca.ulaval.glo4002.billing.api.dto.payment;

import ca.ulaval.glo4002.billing.domain.payment.PaymentMethod;
import ca.ulaval.glo4002.billing.domain.payment.PaymentSource;

public class RequestPaymentDto {

  private Long clientId;
  private float amount;
  private PaymentMethod paymentMethod;

  public Long getClientId() {
    return clientId;
  }

  public RequestPaymentDto() {
  }

  public RequestPaymentDto(Long clientId, float amount, String account, PaymentSource source) {
    this.clientId = clientId;
    this.amount = amount;
    this.paymentMethod = new PaymentMethod(account, source);
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
