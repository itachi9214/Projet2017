package ca.ulaval.glo4002.payment.domain.bill;

import java.math.BigDecimal;

public class Bill {

  private Long billNumber;
  private BigDecimal paidPrice;

  public Bill() {
  }

  public Bill(Long billNumber, BigDecimal paidPrice) {
    this.billNumber = billNumber;
    this.paidPrice = paidPrice;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal getPaidPrice() {
    return paidPrice;
  }

  public void setPaidPrice(BigDecimal paidPrice) {
    this.paidPrice = paidPrice;
  }

  public void addPayment(float amount) {
    this.paidPrice = this.paidPrice.add(new BigDecimal(amount));
  }

}
