package ca.ulaval.glo4002.billing.domain.Submission;

import java.time.LocalDate;
import java.util.List;

public class Bill extends Submission {

  private LocalDate effectiveDate;
  private LocalDate expectedPaiement;

  public Bill(Id billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDate.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
  }

  public Bill(Id billNumber) {
    super(billNumber);
  }

  public Bill(DueTerm dueTerm) {
    super(dueTerm);
    this.effectiveDate = LocalDate.now();
    this.expectedPaiement = calculateExpectedPaiementDate();
  }

  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }
  
  public LocalDate getExpectedPaiement() {
	return expectedPaiement;
}

public void setExpectedPaiement(LocalDate expectedPaiement) {
	this.expectedPaiement = expectedPaiement;
}

public LocalDate calculateExpectedPaiementDate() {
    expectedPaiement = effectiveDate;
    if (dueTerm.equals(DueTerm.IMMEDIATE)) {
      expectedPaiement = effectiveDate;
    } else if (dueTerm.equals(DueTerm.DAYS30)) {
      expectedPaiement = effectiveDate.plusMonths(1);
    } else if (dueTerm.equals(DueTerm.DAYS90)) {
      expectedPaiement = effectiveDate.plusMonths(3);
    }
    return expectedPaiement;
  }

}
