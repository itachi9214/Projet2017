package ca.ulaval.glo4002.payment.domain.identity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IdentityTest {

  private Identity identity;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    identity = new Identity();

    assertNotNull(identity.getNumber());
  }

  @Test
  public void givenDifferentIdentityWhenEqualsThenReturnsFalse() {
    identity = new Identity(3L);
    Identity differentIdentity = new Identity();

    assertFalse(differentIdentity.equals(identity));
  }

  @Test
  public void givenEqualsIdentityWhenEqualsThenReturnsTrue() {
    identity = new Identity(3L);
    Identity equalsIdentity = new Identity(3L);

    assertTrue(equalsIdentity.equals(identity));
  }

}
