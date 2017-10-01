package ca.ulaval.glo4002.billing.domain.Submission;

import java.math.BigDecimal;
import java.util.List;

public class Submission {

  private Long billNumber;
  private DueTerm dueTerm;
  private Long clientId;
  private List<OrderedProduct> items;
  private BigDecimal billTotal;

  public Submission(Long billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items) {
    super();
    this.billNumber = billNumber;
    this.dueTerm = dueTerm;
    this.clientId = clientId;
    this.items = items;
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

  public BigDecimal getSubmissionTotal() {
    return billTotal;
  }

  public void setSubmissionTotal(BigDecimal billTotal) {
    this.billTotal = billTotal;
  }

  public Long getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(Long billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal calculatePrice() {
    billTotal = new BigDecimal(0);
    for (OrderedProduct product : items) {
      billTotal = billTotal.add(product.calculateTotalPrice());
    }
    return billTotal;
  }

}