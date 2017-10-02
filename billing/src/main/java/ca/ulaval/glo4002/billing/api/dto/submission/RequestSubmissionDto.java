package ca.ulaval.glo4002.billing.api.dto.submission;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;

public class RequestSubmissionDto {

  private Long clientId;
  private Date creationDate;
  private DueTerm dueTerm;
  private List<OrderedProduct> items;

  public RequestSubmissionDto() {
    super();
  }

  public RequestSubmissionDto(Long clientId, Date creationDate, DueTerm dueTerm,
      List<OrderedProduct> items) {
    super();
    this.clientId = clientId;
    this.creationDate = creationDate;
    this.dueTerm = dueTerm;
    this.items = items;
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
