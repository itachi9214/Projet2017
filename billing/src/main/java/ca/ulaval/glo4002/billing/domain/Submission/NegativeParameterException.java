package ca.ulaval.glo4002.billing.domain.Submission;

public class NegativeParameterException extends BillingException {

  private static final long serialVersionUID = -5138753240978046843L;

  public NegativeParameterException() {
    super();
  }

  public NegativeParameterException(String message) {
    super(message);
  }

}
