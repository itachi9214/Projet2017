package ca.ulaval.glo4002.billing.domain.submision;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class OrderedProductTest {

  private static final int PRODUCT_ID = 1;
  private static final float PRODUCT_PRICE = 100.00f;
  private static final String PRODUCT_NOTE = "product's description";
  private static final int ORDERED_PRODUCT_QUANTITY = 2;

  private OrderedProduct orderedProduct;

  @Before
  public void setUp() throws NegativeParameterException {
    orderedProduct = new OrderedProduct(PRODUCT_ID, PRODUCT_PRICE, PRODUCT_NOTE,
        ORDERED_PRODUCT_QUANTITY);
  }

  @Test
  public void whenCalculateProductThenReturnCorrectResult() {
    BigDecimal totalPrice = orderedProduct.calculateTotalPrice();

    assertEquals(new BigDecimal(PRODUCT_PRICE * ORDERED_PRODUCT_QUANTITY), totalPrice);
  }

}
