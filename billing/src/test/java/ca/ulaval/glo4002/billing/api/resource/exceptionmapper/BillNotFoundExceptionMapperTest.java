package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class BillNotFoundExceptionMapperTest {

  private BillNotFoundExceptionMapper billNotFoundExceptionMapper;

  @Mock
  private ExceptionMapperFactory exceptionMapperResponse;
  @Mock
  private BillNotFoundException billNotFoundException;

  @Before
  public void setUp() {
    billNotFoundExceptionMapper = new BillNotFoundExceptionMapper(exceptionMapperResponse);
  }

  @Test
  public void whenToResponseThenVerifyStatusCodeIsBadRequest() {
    billNotFoundExceptionMapper.toResponse(billNotFoundException);

    verify(exceptionMapperResponse).createNotFoundExceptionMapper();
  }

}
