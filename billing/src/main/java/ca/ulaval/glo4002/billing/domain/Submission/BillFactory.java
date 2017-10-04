package ca.ulaval.glo4002.billing.domain.Submission;

import java.util.Date;

public class BillFactory {

  public Bill create(Long billNumber, Date effectiveDate, Date expectedPaiement, DueTerm dueTerm) {
    return new Bill(billNumber, effectiveDate, expectedPaiement, dueTerm);
  }

}
