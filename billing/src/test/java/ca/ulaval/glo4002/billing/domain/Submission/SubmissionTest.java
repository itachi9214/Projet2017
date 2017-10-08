package ca.ulaval.glo4002.billing.domain.Submission;

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
public class SubmissionTest {

  private static final float priceFirstProduct = 10;
  private static final float priceSecondProduct = 20;
  private static final float negativePriceSecondProduct = -20;
  private Submission submission;

  @Mock
  private OrderedProduct firstProduct;

  @Mock
  private OrderedProduct secondProduct;

  @Before
  public void setUp() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(firstProduct);
    items.add(secondProduct);
    submission = new Submission(items);
  }

  @Test
  public void whenCalculatePriceThenPriceIsCorrect() throws NegativeParameterException {
    when(firstProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceFirstProduct));
    when(secondProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceSecondProduct));

    assertEquals(submission.calculatePrice(), new BigDecimal(30));
  }

  @Test(expected = NegativeParameterException.class)
  public void givenNegativeTotalWhenCalculatePriceThenThrowException()
      throws NegativeParameterException {
    when(firstProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceFirstProduct));
    when(secondProduct.calculateTotalPrice())
        .thenReturn(new BigDecimal(negativePriceSecondProduct));

    submission.calculatePrice();
  }

}