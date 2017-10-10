package ca.ulaval.glo4002.billing.domain.identity;

public class IdentityFactory {

  public Identity createAndGenerateId() {
    return new Identity();
  }

  public Identity createIdFromNumber(Long number) {
    return new Identity(number);
  }

}
