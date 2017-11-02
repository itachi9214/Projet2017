package ca.ulaval.glo4002.payment.domain.bill;

public class Bill {

  private Long billNumber;
  private BillState state;

  public Long getBillNumber() {
    return billNumber;
  }

  public BillState getState() {
    return state;
  }

  public void addPaymentAndUpdateState(float amount) {

  }

}
