package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillTest {

  private static final float priceFirstProduct = 10;
  private static final float priceSecondProduct = 20;
  public Bill bill;

  @Mock
  public OrderedProduct firstProduct;

  @Mock
  public OrderedProduct secondProduct;

  @Before
  public void setUp() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(firstProduct);
    items.add(secondProduct);
    bill = new Bill(items);
  }

  @Test
  public void whenCalculateBillThenCalculateTotalPrice() {
    when(firstProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceFirstProduct));
    when(secondProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceSecondProduct));

    assertEquals(bill.calculateBill(), new BigDecimal(30));
  }

}