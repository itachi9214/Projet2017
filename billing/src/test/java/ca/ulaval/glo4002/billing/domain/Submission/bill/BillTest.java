package ca.ulaval.glo4002.billing.domain.Submission.bill;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.Submission.Bill;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;

public class BillTest {

  LocalDateTime effectiveDate;

  @Before
  public void setUp() {
    effectiveDate = LocalDateTime.now();
  }

  @Test
  public void givenDueTermWhenImmediateThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(DueTerm.IMMEDIATE);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDateTime.now());
  }

  @Test
  public void givenDueTermWhenOneMonthThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(DueTerm.DAYS30);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDateTime.now().plusMonths(1));
  }

  @Test
  public void givenDueTermWhenThreeMonthThenCalculateExpectedPaiementDate() {
    Bill bill = new Bill(DueTerm.DAYS90);

    assertEquals(bill.calculateExpectedPaiementDate(), LocalDateTime.now().plusMonths(3));
  }

}
