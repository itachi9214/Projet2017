package ca.ulaval.glo4002.billing.domain.Submission;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Submission {

  private static final String NEGATIVE_TOTAL_EXCEPTION_MESSAGE = "Total price cannot be negative.";
  private static final int MINIMUM_TOTAL_PRICE = 0;
  protected Long billNumber;
  protected DueTerm dueTerm;
  protected Long clientId;
  protected List<OrderedProduct> items;
  protected BigDecimal totalPrice;

  public Submission() {
  }

  public Submission(Long billNumber) {
    this.billNumber = billNumber;
  }

  public Submission(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super();
    this.billNumber = billNumber;
    this.dueTerm = dueTerm;
    this.clientId = clientId;
    this.items = items;
    this.calculatePrice();
  }

  public Submission(DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    this(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, dueTerm, clientId, items);
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

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
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
      throw new NegativeParameterException(NEGATIVE_TOTAL_EXCEPTION_MESSAGE);
    }
  }

}