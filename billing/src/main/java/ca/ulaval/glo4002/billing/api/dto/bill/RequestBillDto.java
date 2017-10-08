package ca.ulaval.glo4002.billing.api.dto.bill;

public class RequestBillDto {
  private Long billNumber;

  public long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

}
