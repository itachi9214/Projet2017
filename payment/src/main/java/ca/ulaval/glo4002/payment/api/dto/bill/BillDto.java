package ca.ulaval.glo4002.payment.api.dto.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDto {

  private Long billNumber;
  private BigDecimal totalPrice;
  private LocalDateTime expectedPaiement;
  private Long clientId;
  private Boolean state;

  public BillDto() {
  }

  public BillDto(Long billNumber, BigDecimal totalPrice, LocalDateTime expectedPaiement,
      Long clientId, Boolean state) {

    this.billNumber = billNumber;
    this.totalPrice = totalPrice;
    this.expectedPaiement = expectedPaiement;
    this.clientId = clientId;
    this.state = state;
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

  public LocalDateTime getExpectedPaiement() {
    return expectedPaiement;
  }

  public void setExpectedPaiement(LocalDateTime expectedPaiement) {
    this.expectedPaiement = expectedPaiement;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public Boolean getStade() {
    return state;
  }

  public void setStade(Boolean state) {
    this.state = state;
  }

}
