package ca.ulaval.glo4002.payment.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

  private static final BillState STATE_UNPAID = BillState.UNPAID;
  private static final long CLIENT_ID = 2L;
  private static final BigDecimal PRICE_PAID = new BigDecimal(0);
  private static final BigDecimal PRICE_TO_PAY = new BigDecimal(50);
  private static final long BILL_NUMBER = 1L;
  private static final float INSUFFICIENT_AMOUNT = 25;
  private static final float SUFFICIENT_AMOUNT = 50;

  private Bill bill;

  @Before
  public void setUp() {
    bill = new Bill(BILL_NUMBER, PRICE_TO_PAY, PRICE_PAID, CLIENT_ID, STATE_UNPAID);
  }

  @Test
  public void whenAddPaymentAndUpdateStateThenPaymentIsAdded() {
    bill.addPaymentAndUpdateState(INSUFFICIENT_AMOUNT);

    assertEquals(new BigDecimal(INSUFFICIENT_AMOUNT), bill.getPaidPrice());
  }

  @Test
  public void givenInsufficientPaymentWhenAddPaymentAndUpdateStateThenStateIsUnpaid() {
    bill.addPaymentAndUpdateState(INSUFFICIENT_AMOUNT);

    assertEquals(STATE_UNPAID, bill.getBillState());
  }

  @Test
  public void givenSufficientPaymentWhenAddPaymentAndUpdateStateThenStateIsNowPaid() {
    bill.addPaymentAndUpdateState(SUFFICIENT_AMOUNT);

    assertEquals(BillState.PAID, bill.getBillState());
  }

}
