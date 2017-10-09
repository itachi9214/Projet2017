package ca.ulaval.glo4002.billing.domain.Submission;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class BillTest {

  private LocalDateTime effectiveDate;
  private Bill bill;

  @Test
  public void givenDueTermWhenImmediateThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.IMMEDIATE);
    effectiveDate = bill.getEffectiveDate();

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate());
  }

  @Test
  public void givenDueTermWhenOneMonthThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.DAYS30);
    effectiveDate = bill.getEffectiveDate();

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate().plusMonths(1));
  }

  @Test
  public void givenDueTermWhenThreeMonthThenCalculateExpectedPaiementDate() {
    bill = new Bill(DueTerm.DAYS90);
    effectiveDate = bill.getEffectiveDate();

    assertEquals(bill.calculateExpectedPaiementDate(), bill.getEffectiveDate().plusMonths(3));
  }

}
