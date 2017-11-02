package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.resource.exceptionmapper.ExceptionMapperResponse;
import ca.ulaval.glo4002.billing.api.resource.exceptionmapper.ProductNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ProductNotFoundExceptionMapperTest {

  private static final int ID_PRODUCT = 1;

  private ProductNotFoundExceptionMapper productNotFoundExceptionMapper;
  private ProductNotFoundException productNotFoundException;

  @Mock
  private ExceptionMapperResponse exceptionMapperResponse;

  @Before
  public void setUp() {
    productNotFoundExceptionMapper = new ProductNotFoundExceptionMapper(exceptionMapperResponse);
    productNotFoundException = new ProductNotFoundException(ID_PRODUCT);
  }

  @Test
  public void givenProductNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    productNotFoundExceptionMapper.toResponse(productNotFoundException);

    verify(exceptionMapperResponse).createBadRequestExceptionMapper(anyString(), anyString(),
        anyString());
  }

}
