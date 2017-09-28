package ca.ulaval.glo4002.billing.domain.bill;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillTest {

  private static final int priceFirstProduct = 10;
  private static final int priceSecondProduct = 20;
  public Bill bill;

  @Mock
  public OrderedProduct firstProduct;

  @Mock
  public OrderedProduct secondProduct;

  @Before
  public void setUp() {
    bill = new Bill(firstProduct, secondProduct);
  }

  @Test
  public void whenCalculateBillThenCalculateTotalPrice() {
    when(firstProduct.calculateProduct()).thenReturn(new BigDecimal(priceFirstProduct));
    when(secondProduct.calculateProduct()).thenReturn(new BigDecimal(priceSecondProduct));

    bill.calculateBill();

    verify(firstProduct).calculateProduct();
    verify(secondProduct).calculateProduct();

  }

}