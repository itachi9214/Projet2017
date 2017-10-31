package ca.ulaval.glo4002.billing.domain.identity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IdentityFactoryTest {

  private static final Long A_NUMBER = 13L;

  private IdentityFactory identityFactory;

  @Before
  public void setUp() {
    identityFactory = new IdentityFactory();
  }

  @Test
  public void whenCreateAndGenerateIdThenIdShouldNotBeNull() {
    Identity identity = identityFactory.createId();

    assertNotNull(identity);
  }

  @Test
  public void whenCreateIdFromNumberThenNumberIsSetCorrectly() {
    Identity identity = identityFactory.createIdFromNumber(A_NUMBER);

    assertEquals(A_NUMBER, identity.getNumber());
  }

}
