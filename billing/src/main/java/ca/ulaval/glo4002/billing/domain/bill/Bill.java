package ca.ulaval.glo4002.billing.domain.bill;

import java.time.LocalDateTime;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.id.Id;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

public class Bill extends Submission {

  private LocalDateTime effectiveDate;
  private LocalDateTime expectedPaiement;

  public Bill(Id billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
  }

  public Bill(Id billNumber) {
    super(billNumber);
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
