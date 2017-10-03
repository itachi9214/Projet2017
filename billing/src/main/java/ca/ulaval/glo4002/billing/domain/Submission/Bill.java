package ca.ulaval.glo4002.billing.domain.Submission;

import java.util.Date;
import java.util.List;

public class Bill extends Submission {

  private Date effectiveDate;
  @SuppressWarnings("unused")
  private Date expectedPaiement;

  public Bill(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items) {
    super(billNumber, dueTerm, clientId, items);
    // TODO Auto-generated constructor stub
  }

  public Bill(List<OrderedProduct> items) {
    super(items);
    // TODO Auto-generated constructor stub
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
