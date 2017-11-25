package ca.ulaval.glo4002.billing.api.dto.client;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {

  private Long clientNumber;
  private Instant creationDate;
  private DueTerm defaultDueTerm;
  private String fullName;

  public ClientDto() {
  }

  public ClientDto(Long clientNumber, Instant creationDate, DueTerm defaultDueTerm,
      String fullName) {
    this.clientNumber = clientNumber;
    this.creationDate = creationDate;
    this.defaultDueTerm = defaultDueTerm;
    this.fullName = fullName;
  }

  public Long getId() {
    return clientNumber;
  }

  public void setId(Long clientNumber) {
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
