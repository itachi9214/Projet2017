package ca.ulaval.glo4002.billing.api.dto.bill;

import java.math.BigDecimal;

public class BillForPaymentDto {

  private Long billNumber;
  private BigDecimal remainingAmount;

  public BillForPaymentDto() {
  }

  public BillForPaymentDto(Long billNumber, BigDecimal remainingAmount) {
    this.billNumber = billNumber;
    this.remainingAmount = remainingAmount;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public BigDecimal getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(BigDecimal remainingAmount) {
    this.remainingAmount = remainingAmount;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

}
