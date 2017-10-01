package ca.ulaval.glo4002.billing.domain.Submission;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;

public class OrderedProductTest {

  private static final int productId = 1;
  private static final float productPrice = 100.00f;
  private static final String productNote = "product's description";
  private static final int orderedProductQuantity = 2;
  private OrderedProduct orderedProduct;

  @Before
  public void setUp() {
    orderedProduct = new OrderedProduct(productId, productPrice, productNote,
        orderedProductQuantity);
  }

  @Test
  public void whenCalculateProductThenReturnCorrectResult() {
    assertEquals(new BigDecimal(200), orderedProduct.calculateTotalPrice());
  }

}