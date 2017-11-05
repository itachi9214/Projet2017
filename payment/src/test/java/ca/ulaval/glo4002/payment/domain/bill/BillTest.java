package ca.ulaval.glo4002.payment.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

  private static final float INSUFFICIENT_AMOUNT = 25;
  private static final float SUFFICIENT_AMOUNT = 50;
  private Bill bill;

  @Before
  public void setUp() {
    bill = new Bill(1L, new BigDecimal(50), new BigDecimal(0), LocalDateTime.now(), 2L,
        BillState.UNPAID);
  }

  @Test
  public void whenAddPaymentAndUpdateStateThenPaymentIsAdded() {
    bill.addPaymentAndUpdateState(INSUFFICIENT_AMOUNT);

    assertEquals(new BigDecimal(INSUFFICIENT_AMOUNT), bill.getPricePaid());
  }

  @Test
  public void givenInsufficientPaymentWhenAddPaymentAndUpdateStateThenStateIsUnpaid() {
    bill.addPaymentAndUpdateState(INSUFFICIENT_AMOUNT);

    assertEquals(BillState.UNPAID, bill.getState());
  }

  @Test
  public void givenSufficientPaymentWhenAddPaymentAndUpdateStateThenStateIsNowPaid() {
    bill.addPaymentAndUpdateState(SUFFICIENT_AMOUNT);

    assertEquals(BillState.PAID, bill.getState());
  }

}
