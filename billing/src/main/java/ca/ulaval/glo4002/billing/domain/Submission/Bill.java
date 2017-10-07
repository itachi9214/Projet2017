package ca.ulaval.glo4002.billing.domain.Submission;

import java.util.Date;
import java.util.List;

public class Bill extends Submission {

  private Date effectiveDate;
  private Date expectedPaiement;

  public Bill(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items) {
    super(billNumber, dueTerm, clientId, items);
  }

  public Bill(Long billNumber) {
    super(billNumber);
  }

  public Bill(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items,
      Date effectiveDate, Date expectedPaiement) {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = effectiveDate;
    this.expectedPaiement = expectedPaiement;
  }

  public Bill(Long billNumber, Date effectiveDate, Date expectedPaiement, DueTerm dueTerm) {
    this.billNumber = billNumber;
    this.effectiveDate = effectiveDate;
    this.expectedPaiement = expectedPaiement;
    this.dueTerm = dueTerm;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpectedPaiement() {
    return expectedPaiement;
  }

  public void setExpectedPaiement(Date expectedPaiement) {
    this.expectedPaiement = expectedPaiement;
  }

  @SuppressWarnings("deprecation")
  public Date calculateExpectedPaiementDate() {
    Date expectedPaiementDate = new Date(0);
    if (dueTerm.equals(DueTerm.IMMEDIATE)) {
      expectedPaiementDate = effectiveDate;
    } else if (dueTerm.equals(DueTerm.DAYS30)) {
      expectedPaiementDate.setMonth(effectiveDate.getMonth() + 1);
    } else if (dueTerm.equals(DueTerm.DAYS90)) {
      expectedPaiementDate.setMonth(effectiveDate.getMonth() + 3);
    }
    return expectedPaiementDate;
  }

}
