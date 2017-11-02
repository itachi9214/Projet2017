package ca.ulaval.glo4002.payment.domain.identity;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ulaval.glo4002.payment.domain.Identity.Identity;

public class IdentityTest {

  private Identity identity;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    identity = new Identity();

    assertNotNull(identity.getNumber());
  }

}
