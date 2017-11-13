package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@RunWith(MockitoJUnitRunner.class)
public class BillAlreadyExistsExceptionMapperTest {

  private static final String ENTITY = "invoice";
  private static final String DESCRIPTION = "Invoice already accepted";
  private static final String ERROR = "wrong status";

  private BillAlreadyExistsException billAlreadyExistsException;
  private BillAlreadyExistsExceptionMapper billAlreadyExistsExceptionMapper;

  @Mock
  private ExceptionMapperFactory exceptionMapperFactory;

  @Before
  public void setUp() {
    billAlreadyExistsExceptionMapper = new BillAlreadyExistsExceptionMapper(
        exceptionMapperFactory);
    billAlreadyExistsException = new BillAlreadyExistsException();
  }

  @Test
  public void whenToResponseThenVerifyCreateBadResquestExceptionMapperIsCalled() {
    billAlreadyExistsExceptionMapper.toResponse(billAlreadyExistsException);

    verify(exceptionMapperFactory).createBadRequestExceptionMapper(ERROR, DESCRIPTION, ENTITY);
  }

}
