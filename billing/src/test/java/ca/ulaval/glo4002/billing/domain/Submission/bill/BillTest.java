package ca.ulaval.glo4002.billing.domain.Submission.bill;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;

public class BillTest {

  LocalDate effectiveDate;

  @Before
  public void setUp() {
    effectiveDate = LocalDate.of(2017, 2, 10);
  }

  @Test
  public void givenDueTermWhenImmediateThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(effectiveDate, DueTerm.IMMEDIATE);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDate.of(2017, 2, 10));
  }

  @Test
  public void givenDueTermWhenOneMonthThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(effectiveDate, DueTerm.DAYS30);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDate.of(2017, 3, 10));
  }

  @Test
  public void givenDueTermWhenThreeMonthThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(effectiveDate, DueTerm.DAYS90);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDate.of(2017, 5, 10));
  }

}
