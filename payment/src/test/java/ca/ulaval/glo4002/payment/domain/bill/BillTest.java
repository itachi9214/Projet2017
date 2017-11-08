package ca.ulaval.glo4002.payment.domain.bill;

import java.math.BigDecimal;

import org.junit.Before;

public class BillTest {

  private static final BigDecimal PRICE_PAID = new BigDecimal(0);
  private static final long BILL_NUMBER = 1L;
  private static final float AMOUNT = 25;

  private Bill bill;

  @Before
  public void setUp() {
    bill = new Bill(BILL_NUMBER, PRICE_PAID);
  }

  // @Test
  // public void whenAddPaymentThenPaymentIsAdded() {
  // bill.addPayment(AMOUNT);
  //
  // assertEquals(new BigDecimal(AMOUNT), bill.getPaidPrice());
  // }

}
