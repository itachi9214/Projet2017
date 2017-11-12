package ca.ulaval.glo4002.payment.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

  private static final double FLOAT_DIFFERENCE = 0.001;
  private static final BigDecimal REMAINING_AMOUNT = new BigDecimal(50);
  private static final long BILL_NUMBER = 1L;
  private static final float LOWER_AMOUNT = 25;
  private static final float BIGGER_AMOUNT = 75;

  private Bill bill;

  @Before
  public void setUp() {
    bill = new Bill(BILL_NUMBER, REMAINING_AMOUNT);
  }

  @Test
  public void whenSubtractPaidAmountThenPaymentIsSubtracted() {
    bill.substractPaidAmount(LOWER_AMOUNT);

    assertEquals(new BigDecimal(LOWER_AMOUNT), bill.getRemainingAmount());
  }

  @Test
  public void givenRemainingAmountLowerThanAmountWhenGetPriceThatCanBePaidThenReturnsRemainingAmount() {
    float result = bill.getPriceThatCanBePaid(BIGGER_AMOUNT);

    assertEquals(REMAINING_AMOUNT.floatValue(), result, FLOAT_DIFFERENCE);
  }

  @Test
  public void givenRemainingAmountLowerBiggerThanAmountWhenGetPriceThatCanBePaidThenReturnsAmount() {
    float result = bill.getPriceThatCanBePaid(LOWER_AMOUNT);

    assertEquals(LOWER_AMOUNT, result, FLOAT_DIFFERENCE);
  }

}
