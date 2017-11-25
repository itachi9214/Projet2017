package ca.ulaval.glo4002.billing.domain.submitting;

public enum DueTerm {

  IMMEDIATE(0), DAYS30(1), DAYS90(3);

  private int monthsQuantity;

  DueTerm(int monthsQuantity) {
    this.monthsQuantity = monthsQuantity;
  }

  public int getMonthsQuantity() {
    return monthsQuantity;
  }

}
