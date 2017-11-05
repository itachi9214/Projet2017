package ca.ulaval.glo4002.payment.domain.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {

  private Long billNumber;
  private BigDecimal totalPrice;
  private BigDecimal pricePaid;
  private LocalDateTime expectedPaiement;
  private Long clientId;
  private BillState state;

  public Bill() {
  }

  public Bill(Long billNumber, BigDecimal totalPrice, BigDecimal pricePaid,
      LocalDateTime expectedPaiement, Long clientId, BillState state) {
    this.billNumber = billNumber;
    this.totalPrice = totalPrice;
    this.pricePaid = pricePaid;
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

  public BigDecimal getPricePaid() {
    return pricePaid;
  }

  public void setPricePaid(BigDecimal pricePaid) {
    this.pricePaid = pricePaid;
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

  public BillState getState() {
    return state;
  }

  public void setState(BillState state) {
    this.state = state;
  }

  public void addPaymentAndUpdateState(float amount) {
    this.pricePaid = this.pricePaid.add(new BigDecimal(amount));

    if (this.pricePaid.compareTo(this.totalPrice) >= 0) {
      this.state = BillState.PAID;
    }
  }

}
