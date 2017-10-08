package ca.ulaval.glo4002.billing.domain.Submission;

public class IdFactory {

  public Id createAndGenerateId() {
    return new Id();
  }

  public Id createIdFromNumber(Long number) {
    return new Id(number);
  }

}
