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

import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionTest {

  private static final float priceFirstProduct = 10;
  private static final float priceSecondProduct = 20;
  public Submission submission;

  @Mock
  public OrderedProduct firstProduct;

  @Mock
  public OrderedProduct secondProduct;

  @Before
  public void setUp() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(firstProduct);
    items.add(secondProduct);
    submission = new Submission(items);
  }

  @Test
  public void whenCalculateSubmissionThenCalculateTotalPrice() {
    when(firstProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceFirstProduct));
    when(secondProduct.calculateTotalPrice()).thenReturn(new BigDecimal(priceSecondProduct));

    assertEquals(submission.calculatePrice(), new BigDecimal(30));
  }

}