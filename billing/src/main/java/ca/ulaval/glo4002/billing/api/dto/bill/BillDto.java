package ca.ulaval.glo4002.billing.api.dto.bill;

import java.time.LocalDate;

import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;

public class BillDto {

  private Long id;
  private LocalDate effectiveDate;
  private LocalDate expectedPayment;
  private DueTerm dueTerm;
  private String url;

  public BillDto(Long id, LocalDate effectiveDate, LocalDate expectedPayment, DueTerm dueTerm,
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

  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(LocalDate effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public LocalDate getExpectedPayment() {
    return expectedPayment;
  }

  public void setExpectedPayment(LocalDate expectedPayment) {
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
