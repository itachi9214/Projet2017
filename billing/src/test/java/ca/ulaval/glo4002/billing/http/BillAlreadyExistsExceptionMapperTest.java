package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

public class BillAlreadyExistsExceptionMapperTest {

  private BillAlreadyExistsException billAlreadyExistsException;
  private BillAlreadyExistsExceptionMapper billAlreadyExistsExceptionMapper;

  @Before
  public void setUp() {
    billAlreadyExistsExceptionMapper = new BillAlreadyExistsExceptionMapper();
    billAlreadyExistsException = new BillAlreadyExistsException();
  }

  @Test
  public void givenNegativeParameterExceptioWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = billAlreadyExistsExceptionMapper.toResponse(billAlreadyExistsException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
