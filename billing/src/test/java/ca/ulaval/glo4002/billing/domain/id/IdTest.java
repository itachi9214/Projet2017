package ca.ulaval.glo4002.billing.domain.id;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.id.Id;

public class IdTest {

  private Id id;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    id = new Id();

    assertNotNull(id.getNumber());
  }

}
