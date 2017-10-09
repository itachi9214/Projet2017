package ca.ulaval.glo4002.billing.domain.Submission;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class IdTest {

  private Id id;

  @Test
  public void whenNewIdToGenerateThenNumberShouldBeSet() {
    id = new Id();

    assertNotNull(id.getNumber());
  }

}
