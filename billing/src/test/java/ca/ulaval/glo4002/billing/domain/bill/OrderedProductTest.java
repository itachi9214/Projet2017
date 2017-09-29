package ca.ulaval.glo4002.billing.domain.bill;

import org.junit.Before;
import org.junit.Test;

public class OrderedProductTest {

  private int productId = 1;
  private float productPrice = 100.00f;
  private String productNote;
  private int orderedProductQuantity = 2;
  OrderedProduct orderedProduct;
  Bill bill;

  @Before
  public void setUp() {
    bill = new Bill();
    orderedProduct = new OrderedProduct(productId, productPrice, productNote,
        orderedProductQuantity);
  }

  @Test
  public void whenCalculateProductThenReturnCorrectResult() {
    assertEquals(new BigDecimal(200), orderedProduct.calculateTotalPrice());
  }

  }
}
