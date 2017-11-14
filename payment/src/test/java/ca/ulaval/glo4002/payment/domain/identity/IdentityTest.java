package ca.ulaval.glo4002.payment.domain.identity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IdentityTest {

  private static final long ANY_NUMBER = 3L;

  private Identity identity;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    identity = new Identity();

    assertNotNull(identity.getNumber());
  }

  @Test
  public void givenDifferentIdentitiesWhenEqualsThenReturnsFalse() {
    identity = new Identity(ANY_NUMBER);
    Identity differentIdentity = new Identity();

    assertFalse(differentIdentity.equals(identity));
  }

  @Test
  public void givenEqualIdentitiesWhenEqualsThenReturnsTrue() {
    identity = new Identity(ANY_NUMBER);
    Identity equalsIdentity = new Identity(ANY_NUMBER);

    assertTrue(equalsIdentity.equals(identity));
  }

}
