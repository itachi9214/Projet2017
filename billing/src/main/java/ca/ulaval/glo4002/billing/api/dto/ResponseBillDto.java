package ca.ulaval.glo4002.billing.api.dto;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.bill.DueTerm;

public class ResponseBillDto {

  private Long billNumber;
  private BigDecimal total;
  private DueTerm dueTerm;
  private String url;

  public ResponseBillDto(Long billNumber, BigDecimal total, DueTerm dueTerm, String url) {
    this.billNumber = billNumber;
    this.total = total;
    this.dueTerm = dueTerm;
    this.url = url;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public void setDueTerm(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
