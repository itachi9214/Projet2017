package ca.ulaval.glo4002.billing.domain.Submission;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Submission {

  protected Long billNumber;
  protected DueTerm dueTerm;
  protected Long clientId;
  protected List<OrderedProduct> items;
  protected BigDecimal totalPrice;

  public Submission() {
  }

  public Submission(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items) {
    super();
    this.billNumber = billNumber;
    this.dueTerm = dueTerm;
    this.clientId = clientId;
    this.items = items;
  }

  public Submission(DueTerm dueTerm, Long clientId, List<OrderedProduct> items) {
    this(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, dueTerm, clientId, items);
  }

  public Submission(List<OrderedProduct> items) {
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

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal calculatePrice() {
    totalPrice = new BigDecimal(0);
    for (OrderedProduct product : items) {
      totalPrice = totalPrice.add(product.calculateTotalPrice());
    }
    return totalPrice;
  }

}