package ca.ulaval.glo4002.payment.domain.payment;

public class Payment {

  private long id;
  private float amount;

  public Payment(long id, float amount) {
    this.id = id;
    this.amount = amount;
  }

  public long getId() {
    return id;
  }

  public float getAmount() {
    return amount;
  }

}
