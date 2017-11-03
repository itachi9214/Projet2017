package ca.ulaval.glo4002.billing.api.dto.bill;

import java.math.BigDecimal;

public class BillStateDto {

  private Long id;
  private Long clientId;
  private String effectiveDate;
  private BigDecimal total;
  private String billState;

  public BillStateDto(Long id, Long clientId, String effectiveDate, BigDecimal total,
      String billState) {
    super();
    this.id = id;
    this.clientId = clientId;
    this.effectiveDate = effectiveDate;
    this.total = total;
    this.billState = billState;
  }

  public Long getId() {
    return id;
  }

  public Long getClientId() {
    return clientId;
  }

  public String getEffectiveDate() {
    return effectiveDate;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public String getBillState() {
    return billState;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public void setBillState(String billState) {
    this.billState = billState;
  }

}
