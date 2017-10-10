package ca.ulaval.glo4002.billing.infrastructure.bill;

import ca.ulaval.glo4002.billing.domain.bill.BillingException;

public class BillAlreadyExistsException extends BillingException {

  private static final long serialVersionUID = 1L;

  public BillAlreadyExistsException() {
    super();
  }

  public BillAlreadyExistsException(String message) {
    super(message);
  }

}
