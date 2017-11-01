package ca.ulaval.glo4002.payment.api.dto;

import java.math.BigDecimal;

import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;

public class RequestPaymentDto {

  private Long clientId;
  private BigDecimal amount;
  private PaymentMethod paymentMethod;

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

}
