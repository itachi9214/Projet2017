package ca.ulaval.glo4002.payment.domain.bill;

import java.math.BigDecimal;

public class Bill {

  private Long billNumber;
  private BigDecimal remainingAmount;

  public Bill() {
  }

  public Bill(Long billNumber, BigDecimal remainingAmount) {
    this.billNumber = billNumber;
    this.remainingAmount = remainingAmount;
  }

  public BigDecimal getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(BigDecimal remainingAmount) {
    this.remainingAmount = remainingAmount;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public void substractPaidAmount(float amount) {
    this.remainingAmount = this.remainingAmount.subtract(new BigDecimal(amount));
  }

  public float getPriceThatCanBePaid(float paymentAmount) {
    float priceThatCanBePaid;
    if (isRemainingAmountLowerThan(paymentAmount)) {
      priceThatCanBePaid = remainingAmount.floatValue();
    } else {
      priceThatCanBePaid = paymentAmount;
    }
    return priceThatCanBePaid;
  }

  private boolean isRemainingAmountLowerThan(float paymentAmount) {
    return remainingAmount.floatValue() - paymentAmount < 0;
  }

}
