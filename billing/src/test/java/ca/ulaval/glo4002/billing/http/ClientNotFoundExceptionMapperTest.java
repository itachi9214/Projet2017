package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class ClientNotFoundExceptionMapperTest {
  ClientNotFoundException clientNotFoundException;
  ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;
  private static long idClient = 1;

  @Before
  public void setUp() {
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper();
    clientNotFoundException = new ClientNotFoundException(idClient);
  }

  @Test
  public void givenClientNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    Response codeError = clientNotFoundExceptionMapper.toResponse(clientNotFoundException);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
