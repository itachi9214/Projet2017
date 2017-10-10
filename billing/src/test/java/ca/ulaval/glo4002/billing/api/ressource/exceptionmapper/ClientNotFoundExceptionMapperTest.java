package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.api.ressource.exceptionmapper.ClientNotFoundExceptionMapper;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;

public class ClientNotFoundExceptionMapperTest {

  private static final long ID_CLIENT = 1L;

  private ClientNotFoundException clientNotFoundException;
  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;

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
