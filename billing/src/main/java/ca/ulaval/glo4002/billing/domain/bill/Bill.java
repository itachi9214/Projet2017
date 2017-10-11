package ca.ulaval.glo4002.billing.domain.bill;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@Entity
public class Bill extends Submission {

  @Temporal(TemporalType.DATE)
  private LocalDateTime effectiveDate;
  @Temporal(TemporalType.DATE)
  private LocalDateTime expectedPaiement;

  public Bill(Identity billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
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

}
