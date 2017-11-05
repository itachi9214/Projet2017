package ca.ulaval.glo4002.billing.api.dto.bill;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.bill.BillState;

public class BillForPaymentDto {

  private Long billNumber;
  private Long clientId;
  private BigDecimal totalPrice;
  private BigDecimal paidPrice;
  private BillState billState;

  public BillForPaymentDto() {
  }

  public BillForPaymentDto(Long billNumber, Long clientId, BigDecimal totalPrice,
      BigDecimal paidPrice, BillState billState) {
    this.billNumber = billNumber;
    this.clientId = clientId;
    this.totalPrice = totalPrice;
    this.paidPrice = paidPrice;
    this.billState = billState;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public Long getClientId() {
    return clientId;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public BillState getBillState() {
    return billState;
  }

  public BigDecimal getPaidPrice() {
    return paidPrice;
  }

  public void setPaidPrice(BigDecimal paidPrice) {
    this.paidPrice = paidPrice;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setBillState(BillState billState) {
    this.billState = billState;
  }

}
