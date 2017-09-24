package ca.ulaval.glo4002.billing.api.dto;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.DueTerm;
import ca.ulaval.glo4002.billing.domain.bill.OrderedProduct;

public class RequestBillDto {

  private Long clientId;
  private Date creationDate;
  private DueTerm dueTerm;
  private List<OrderedProduct> items;

  public RequestBillDto() {
    super();
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public void setDueTerm(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
  }

  public List<OrderedProduct> getItems() {
    return items;
  }

  public void setItems(List<OrderedProduct> items) {
    this.items = items;
  }

}
