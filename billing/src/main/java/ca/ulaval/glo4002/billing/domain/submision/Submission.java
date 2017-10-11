package ca.ulaval.glo4002.billing.domain.submision;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import ca.ulaval.glo4002.billing.domain.identity.Identity;

@Entity
public class Submission {

  private static final int MINIMUM_TOTAL_PRICE = 0;

  @EmbeddedId
  protected Identity billNumber;
  @Enumerated
  protected DueTerm dueTerm;
  protected Long clientId;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn
  protected List<OrderedProduct> items;
  protected BigDecimal totalPrice;

  public Submission(Identity billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    this.billNumber = billNumber;
    this.dueTerm = dueTerm;
    this.clientId = clientId;
    this.items = items;
    this.calculatePrice();
  }

  public Submission(List<OrderedProduct> items) {
    this.items = items;
  }

  public Submission(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
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

  public Identity getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Identity billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal calculatePrice() throws NegativeParameterException {
    totalPrice = new BigDecimal(0);
    for (OrderedProduct product : items) {
      totalPrice = totalPrice.add(product.calculateTotalPrice());
    }
    verifyTotalPriceIsNotNegative();
    return totalPrice;
  }

  private void verifyTotalPriceIsNotNegative() throws NegativeParameterException {
    if (totalPrice.floatValue() < MINIMUM_TOTAL_PRICE) {
      throw new NegativeParameterException("Submission total price");
    }
  }

}
