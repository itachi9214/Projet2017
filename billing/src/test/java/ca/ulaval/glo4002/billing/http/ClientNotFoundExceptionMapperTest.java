package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class ClientNotFoundExceptionMapperTest {

  private ClientNotFoundException clientNotFoundException;
  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;
  private static final long ID_CLIENT = 1L;

  @Before
  public void setUp() {
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper();
    clientNotFoundException = new ClientNotFoundException(ID_CLIENT);
  }

  @Test
  public void givenClientNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = clientNotFoundExceptionMapper.toResponse(clientNotFoundException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
