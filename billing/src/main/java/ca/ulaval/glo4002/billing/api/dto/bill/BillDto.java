package ca.ulaval.glo4002.billing.api.dto.bill;

import java.util.Date;

import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;

public class BillDto {

  private Long id;
  private Date effectiveDate;
  private Date expectedPayment;
  private DueTerm dueTerm;
  private String url;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpectedPayment() {
    return expectedPayment;
  }

  public void setExpectedPayment(Date expectedPayment) {
    this.expectedPayment = expectedPayment;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public void setDueTerm(DueTerm dueTerm) {
    this.dueTerm = dueTerm;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
