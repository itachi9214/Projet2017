package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@RunWith(MockitoJUnitRunner.class)
public class BillAlreadyExistsExceptionMapperTest {

  private BillAlreadyExistsException billAlreadyExistsException;
  private BillAlreadyExistsExceptionMapper billAlreadyExistsExceptionMapper;

  @Mock
  private ExceptionMapperResponse exceptionMapperResponse;

  @Before
  public void setUp() {
    billAlreadyExistsExceptionMapper = new BillAlreadyExistsExceptionMapper(
        exceptionMapperResponse);
    billAlreadyExistsException = new BillAlreadyExistsException();
  }

  @Test
  public void whenToResponseThenVerifyCreateBadResquestExceptionMapperIsCalled() {
    billAlreadyExistsExceptionMapper.toResponse(billAlreadyExistsException);

    verify(exceptionMapperResponse).createBadRequestExceptionMapper(anyString(), anyString(),
        anyString());
  }

}
