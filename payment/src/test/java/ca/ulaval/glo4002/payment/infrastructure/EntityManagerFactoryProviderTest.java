package ca.ulaval.glo4002.payment.infrastructure;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;

public class EntityManagerFactoryProviderTest {

  private static EntityManagerFactory instance;

  @Test
  public void whenGetFactoryThenInstanceIsCreated() {
    instance = EntityManagerFactoryProvider.getFactory();

    assertNotNull(instance);
  }

  @Test
  public void givenNullInstanceWhenGetFactoryThenInstanceIsNotNull() {
    instance = null;
    instance = EntityManagerFactoryProvider.getFactory();

    assertNotNull(instance);
  }

}
