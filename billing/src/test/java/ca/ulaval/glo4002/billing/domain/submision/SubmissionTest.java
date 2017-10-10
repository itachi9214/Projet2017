package ca.ulaval.glo4002.billing.domain.submision;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

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

  private static final float PRICE_FIRTS_PRODUCT = 10;
  private static final float PRICE_SECOND_PRODUCT = 20;
  private static final float NEGATIVE_PRICE_SECOND_PRODUCT = -20;

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
    willReturn(new BigDecimal(PRICE_FIRTS_PRODUCT)).given(firstProduct).calculateTotalPrice();
    willReturn(new BigDecimal(PRICE_SECOND_PRODUCT)).given(secondProduct).calculateTotalPrice();

    assertEquals(submission.calculatePrice(), new BigDecimal(30));
  }

  @Test(expected = NegativeParameterException.class)
  public void givenNegativeTotalWhenCalculatePriceThenThrowException()
      throws NegativeParameterException {
    willReturn(new BigDecimal(PRICE_FIRTS_PRODUCT)).given(firstProduct).calculateTotalPrice();
    willReturn(new BigDecimal(NEGATIVE_PRICE_SECOND_PRODUCT)).given(secondProduct)
        .calculateTotalPrice();

    submission.calculatePrice();
  }

}