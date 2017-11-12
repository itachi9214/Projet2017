package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;

@RunWith(MockitoJUnitRunner.class)
public class BillTest {

  private static final BigDecimal UNSUFFICIENT_AMOUNT = new BigDecimal(5);
  private static final BigDecimal REMANING_AMOUNT = new BigDecimal(0);
  private static final BigDecimal TOTAL_AMOUNT = new BigDecimal(30);
  private static final long CLIENT_ID = 1L;

  private Bill bill;
  private List<OrderedProduct> items;

  @Mock
  private Identity identity;
  @Mock
  private OrderedProduct orderedProduct;

  @Before
  public void setUp() {
    items = new ArrayList<>();
    items.add(orderedProduct);
    willReturn(TOTAL_AMOUNT).given(orderedProduct).calculateTotalPrice();

    bill = new Bill(identity, DueTerm.IMMEDIATE, CLIENT_ID, items);
  }

  @Test
  public void givenImmediateDueTermWhenCalculateExpectedPaiementDateThenIsEffectiveDate() {
    DueTerm dueTerm = DueTerm.IMMEDIATE;
    bill = new Bill(dueTerm);

    LocalDateTime date = bill.calculateExpectedPaymentDate();

    assertEquals(bill.getEffectiveDate(), date);
  }

  @Test
  public void givenOneMonthDueTermWhenCalculateExpectedPaiementDateThenIsEffectiveDatePlusOneMonth() {
    DueTerm dueTerm = DueTerm.DAYS30;
    bill = new Bill(dueTerm);

    LocalDateTime date = bill.calculateExpectedPaymentDate();

    assertEquals(bill.getEffectiveDate().plusMonths(1), date);
  }

  @Test
  public void givenThreeMonthDueTermWhenCalculateExpectedPaiementDateThenIsEffectiveDatePlusTreeMonths() {
    DueTerm dueTerm = DueTerm.DAYS90;
    bill = new Bill(dueTerm);

    LocalDateTime date = bill.calculateExpectedPaymentDate();

    assertEquals(bill.getEffectiveDate().plusMonths(3), date);
  }

  @Test
  public void whenUpdateAfterPaymentThenRemainingAmountIsSetCorrectly() {
    bill.updateAfterPayment(REMANING_AMOUNT);

    assertEquals(REMANING_AMOUNT, bill.getRemainingAmount());
  }

  @Test
  public void givenUnsufficientAmountWhenUpdateAfterPaymentThenStateIsStillUnpaid() {
    bill.updateAfterPayment(UNSUFFICIENT_AMOUNT);

    assertEquals(BillState.UNPAID, bill.getBillState());
  }

  @Test
  public void givenSufficientAmountWhenUpdateAfterPaymentThenStateIsNowPaid() {
    bill.updateAfterPayment(REMANING_AMOUNT);

    assertEquals(BillState.PAID, bill.getBillState());
  }

  @Test
  public void whenCalculateUnpaidAmountThenReturnUnpaidAmount() {
    BigDecimal unpaidAmount = bill.calculateUnpaidAmount();

    assertEquals(TOTAL_AMOUNT, unpaidAmount);
  }

}
