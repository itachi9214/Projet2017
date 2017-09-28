package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bill {

  private Long billNumber;
  private DueTerm dueTerm;
  private Long clientId;
  private List<OrderedProduct> items;
  private BigDecimal billTotal;
  private OrderedProduct firstProduct, secondProduct;

  public Bill() {
    super();
    items = new ArrayList<>();
    billTotal = new BigDecimal(0);
  }

  public Bill(List<OrderedProduct> items) {
    super();
    billTotal = new BigDecimal(0);
    this.items = items;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public void setDueTerm(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public List<OrderedProduct> getItems() {
    return items;
  }

  public void setProducts(List<OrderedProduct> items) {
    this.items = items;
  }

  public BigDecimal getBillTotal() {
    return billTotal;
  }

  public void setBillTotal(BigDecimal billTotal) {
    this.billTotal = billTotal;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal calculateBill() {
    billTotal = new BigDecimal(0);
    for (OrderedProduct product : items) {
      billTotal = billTotal.add(product.calculateTotalPrice());
    }
    return billTotal;
  }

}
