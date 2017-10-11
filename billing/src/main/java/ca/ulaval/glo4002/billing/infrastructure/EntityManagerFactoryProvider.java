package ca.ulaval.glo4002.billing.infrastructure;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

  private static EntityManagerFactory instance;

  public static EntityManagerFactory getFactory() {
    if (instance == null) {
      instance = Persistence.createEntityManagerFactory("application");
    }
    return instance;
  }

}
