package ca.ulaval.glo4002.billing.domain.identity;

public class IdentityFactory {

  public Identity createId() {
    return new Identity();
  }

  public Identity createIdFromNumber(Long number) {
    return new Identity(number);
  }

}
