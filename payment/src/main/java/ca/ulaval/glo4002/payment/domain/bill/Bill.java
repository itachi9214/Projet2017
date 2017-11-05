package ca.ulaval.glo4002.payment.domain.bill;

import java.math.BigDecimal;

public class Bill {

  private Long billNumber;
  private BigDecimal totalPrice;
  private BigDecimal paidPrice;
  private Long clientId;
  private BillState billState;

  public Bill() {
  }

  public Bill(Long billNumber, BigDecimal totalPrice, BigDecimal paidPrice, Long clientId,
      BillState billState) {
    this.billNumber = billNumber;
    this.totalPrice = totalPrice;
    this.paidPrice = paidPrice;
    this.clientId = clientId;
    this.billState = billState;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public BigDecimal getPaidPrice() {
    return paidPrice;
  }

  public void setPaidPrice(BigDecimal paidPrice) {
    this.paidPrice = paidPrice;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public BillState getBillState() {
    return billState;
  }

  public void setBillState(BillState billState) {
    this.billState = billState;
  }

  public void addPaymentAndUpdateState(float amount) {
    this.paidPrice = this.paidPrice.add(new BigDecimal(amount));

    if (this.paidPrice.compareTo(this.totalPrice) >= 0) {
      this.billState = BillState.PAID;
    }
  }

}
