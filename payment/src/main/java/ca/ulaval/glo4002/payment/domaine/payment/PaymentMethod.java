package ca.ulaval.glo4002.payment.domaine.payment;

public class PaymentMethod {

  private String account;
  private PaymentSource source;

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public PaymentSource getSource() {
    return source;
  }

  public void setSource(PaymentSource source) {
    this.source = source;
  }

}
