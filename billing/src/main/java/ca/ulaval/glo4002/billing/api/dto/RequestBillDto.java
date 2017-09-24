package ca.ulaval.glo4002.billing.api.dto;

import java.util.Date;

import ca.ulaval.glo4002.billing.domain.DueTerm;

public class RequestBillDto {

  private Long cliendId;
  private Date creationDate;
  private DueTerm dueTerm;
  private OrderedProduct[] items;

  public Long getCliendId() {
    return cliendId;
  }

  public void setCliendId(Long cliendId) {
    this.cliendId = cliendId;
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

  public OrderedProduct[] getItems() {
    return items;
  }

  public void setItems(OrderedProduct[] items) {
    this.items = items;
  }

}
