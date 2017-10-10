package ca.ulaval.glo4002.billing.domain.identity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.identity.Identity;

public class IdentityTest {

  private Identity identity;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    identity = new Identity();

    assertNotNull(identity.getNumber());
  }

}
