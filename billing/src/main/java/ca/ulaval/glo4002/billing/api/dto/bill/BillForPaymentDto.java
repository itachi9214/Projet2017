package ca.ulaval.glo4002.billing.api.dto.bill;

import java.math.BigDecimal;

public class BillForPaymentDto {

  private Long billNumber;
  private BigDecimal paidPrice;

  public BillForPaymentDto() {
  }

  public BillForPaymentDto(Long billNumber, BigDecimal paidPrice) {
    this.billNumber = billNumber;
    this.paidPrice = paidPrice;
  }

  public Long getBillNumber() {
    return billNumber;
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

}
