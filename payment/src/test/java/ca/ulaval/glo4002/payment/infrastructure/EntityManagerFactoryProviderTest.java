package ca.ulaval.glo4002.payment.infrastructure;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;

import ca.ulaval.glo4002.payment.infrastructure.EntityManagerFactoryProvider;

public class EntityManagerFactoryProviderTest {

  private static EntityManagerFactory instance;

  @Test
  public void whenGetFactoryThenInstanceCreated() {
    instance = EntityManagerFactoryProvider.getFactory();

    assertNotNull(instance);
  }

}
