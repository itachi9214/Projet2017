package ca.ulaval.glo4002.billing.domain.submision;

import ca.ulaval.glo4002.billing.domain.bill.BillingException;

public class NegativeParameterException extends BillingException {

  private static final long serialVersionUID = -5138753240978046843L;
  private String parameter;

  public NegativeParameterException(String parameter) {
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }

}
