package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@Entity
public class Bill extends Submission {

  private LocalDateTime effectiveDate;
  private LocalDateTime expectedPaiement;
  private BigDecimal paidAmount = new BigDecimal(0);
  private BillState billState;

  public Bill() {
    super();
  }

  public Bill(Identity billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
    this.billState = BillState.UNPAID;
  }

  public BigDecimal getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
  }

  public BillState getBillState() {
    return billState;
  }

  public void setBillState(BillState billState) {
    this.billState = billState;
  }

  public Bill(DueTerm dueTerm) {
    super(dueTerm);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
  }

  public LocalDateTime getEffectiveDate() {
    return effectiveDate;
  }

  public LocalDateTime getExpectedPaiement() {
    return expectedPaiement;
  }

  public void setExpectedPaiement(LocalDateTime expectedPaiement) {
    this.expectedPaiement = expectedPaiement;
  }

  public LocalDateTime calculateExpectedPaiementDate() {
    this.expectedPaiement = effectiveDate.plusMonths(dueTerm.getMonthsQuantity());
    return expectedPaiement;
  }

  public BigDecimal calculateUnpaidAmount() {
    return totalPrice.subtract(paidAmount);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Bill) {
      return ((Bill) obj).getBillNumber().equals(billNumber);
    }
    return false;
  }

}
