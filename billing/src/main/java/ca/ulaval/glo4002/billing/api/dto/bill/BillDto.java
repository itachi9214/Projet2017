package ca.ulaval.glo4002.billing.api.dto.bill;

import ca.ulaval.glo4002.billing.domain.submision.DueTerm;

public class BillDto {

  private Long id;
  private String effectiveDate;
  private String expectedPayment;
  private DueTerm dueTerm;
  private String url;

  public BillDto(Long id, String effectiveDate, String expectedPayment, DueTerm dueTerm,
      String url) {
    this.id = id;
    this.effectiveDate = effectiveDate;
    this.expectedPayment = expectedPayment;
    this.dueTerm = dueTerm;
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public String getExpectedPayment() {
    return expectedPayment;
  }

  public void setExpectedPayment(String expectedPayment) {
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
