package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.submision.DueTerm;

public class BillTest {

  private Bill bill;

  @Test
  public void givenDueTermWhenImmediateWhenCalculateExpectedPaiementDateThenIsEffectiveDate() {
    DueTerm dueTerm = DueTerm.IMMEDIATE;

    bill = new Bill(dueTerm);

    assertEquals(bill.getEffectiveDate(), bill.calculateExpectedPaymentDate());
  }

  @Test
  public void givenDueTermWhenOneMonthWhenCalculateExpectedPaiementDateThenIsEffectiveDatePlusOneMonth() {
    DueTerm dueTerm = DueTerm.DAYS30;

    bill = new Bill(dueTerm);

    assertEquals(bill.getEffectiveDate().plusMonths(1), bill.calculateExpectedPaymentDate());
  }

  @Test
  public void givenDueTermWhenThreeMonthWhenCalculateExpectedPaiementDateThenIsEffectiveDatePlusTreeMonths() {
    DueTerm dueTerm = DueTerm.DAYS90;

    bill = new Bill(dueTerm);

    assertEquals(bill.getEffectiveDate().plusMonths(3), bill.calculateExpectedPaymentDate());
  }

}
