package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;

public class BillTest {

  private Bill bill;

  @Test
  public void givenDueTermWhenImmediateThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.IMMEDIATE);

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate());
  }

  @Test
  public void givenDueTermWhenOneMonthThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.DAYS30);

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate().plusMonths(1));
  }

  @Test
  public void givenDueTermWhenThreeMonthThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.DAYS90);

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate().plusMonths(3));
  }

}
