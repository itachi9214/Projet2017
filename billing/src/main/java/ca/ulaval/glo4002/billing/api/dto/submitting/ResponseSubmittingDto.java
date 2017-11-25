package ca.ulaval.glo4002.billing.api.dto.submitting;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;

public class ResponseSubmittingDto {

  private Long id;
  private BigDecimal total;
  private DueTerm dueTerm;
  private String url;

  public ResponseSubmittingDto(Long id, BigDecimal total, DueTerm dueTerm, String url) {
    this.id = id;
    this.total = total;
    this.dueTerm = dueTerm;
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
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
