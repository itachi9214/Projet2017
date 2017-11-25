package ca.ulaval.glo4002.billing.infrastructure.bill;

import ca.ulaval.glo4002.billing.domain.submitting.BillingException;

public class BillNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;

  public BillNotFoundException() {
    super();
  }

}
