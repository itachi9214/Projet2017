package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class OrderedProductTest {

  private int productId = 1;
  private float productPrice = 100.00f;
  private String productNote;
  private int orderedProductQuantity = 2;
  OrderedProduct orderedProduct;

  @Before
  public void setUp() {

    orderedProduct = new OrderedProduct(productId, productPrice, productNote,
        orderedProductQuantity);
  }

  @Test
  public void whenCalculateProductThenReturnPrice() {
    BigDecimal result = orderedProduct.calculateProduct();
    assertEquals(new BigDecimal(200), result);
  }

}
