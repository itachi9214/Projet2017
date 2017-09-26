package ca.ulaval.glo4002.billing.api.dto;

import java.time.Instant;

import ca.ulaval.glo4002.billing.domain.DueTerm;

public class ClientDto {

  private Integer clientNumber;
  private Instant creationDate;
  private DueTerm defaultDueTerm;
  private String fullName;

  public Integer getId() {
    return clientNumber;
  }

  public void setId(Integer clientNumber) {
    this.clientNumber = clientNumber;
  }

  public Instant getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Instant creationDate) {
    this.creationDate = creationDate;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public DueTerm getDefaultDueTerm() {
    return defaultDueTerm;
  }

  public void setDefaultTerm(DueTerm defaultDueTerm) {
    this.defaultDueTerm = defaultDueTerm;
  }

}
