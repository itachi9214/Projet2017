package ca.ulaval.glo4002.billing.api.dto.bill;

import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;

public class BillDto {

  private Long id;
  private String effectiveString;
  private String expectedPayment;
  private DueTerm dueTerm;
  private String url;

  public BillDto(Long id, String effectiveString, String expectedPayment, DueTerm dueTerm,
      String url) {
    this.id = id;
    this.effectiveString = effectiveString;
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

  public String getEffectiveString() {
    return effectiveString;
  }

  public void setEffectiveString(String effectiveString) {
    this.effectiveString = effectiveString;
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
