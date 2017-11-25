package ca.ulaval.glo4002.billing.infrastructure.submitting;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.http.CrmHttpProduct;
import ca.ulaval.glo4002.billing.infrastructure.submitting.ProductHttpRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductHttpRepositoryTest {

  private static final int PRODUCT_ID = 1;

  private ProductHttpRepository productHttpRepository;

  @Mock
  private CrmHttpProduct crmHttpProduct;

  @Before
  public void setUp() {
    productHttpRepository = new ProductHttpRepository(crmHttpProduct);
  }

  @Test
  public void whenGetProductDtoThenVerifyProductIsFound() {
    productHttpRepository.getProductDto(PRODUCT_ID);

    verify(crmHttpProduct).getProductDto(PRODUCT_ID);
  }

}
