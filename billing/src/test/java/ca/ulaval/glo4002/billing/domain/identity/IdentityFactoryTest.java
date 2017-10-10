package ca.ulaval.glo4002.billing.domain.id;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class IdFactoryTest {

  private static final Long A_NUMBER = 13L;

  private IdFactory idFactory;

  @Before
  public void setUp() {
    idFactory = new IdFactory();
  }

  @Test
  public void whenCreateAndGenerateIdThenIdShouldNotBeNull() {
    Id id = idFactory.createAndGenerateId();

    assertNotNull(id);
  }

  @Test
  public void whenCreateIdFromNumberThenNumberIsSetCorrectly() {
    Id id = idFactory.createIdFromNumber(A_NUMBER);

    assertEquals(A_NUMBER, id.getNumber());
  }

}
