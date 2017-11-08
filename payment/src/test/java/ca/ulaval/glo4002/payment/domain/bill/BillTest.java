package ca.ulaval.glo4002.payment.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

  private static final BigDecimal REMAINING_AMOUNT = new BigDecimal(50);
  private static final long BILL_NUMBER = 1L;
  private static final float AMOUNT = 25;

  private Bill bill;

  @Before
  public void setUp() {
    bill = new Bill(BILL_NUMBER, REMAINING_AMOUNT);
  }

  @Test
  public void whenSubtractPaidAmountThenPaymentIsSubtracted() {
    bill.substractPaidAmount(AMOUNT);

    assertEquals(new BigDecimal(AMOUNT), bill.getRemainingAmount());
  }

}
