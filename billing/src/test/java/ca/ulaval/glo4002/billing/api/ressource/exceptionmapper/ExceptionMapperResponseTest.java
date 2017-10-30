package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class ExceptionMapperResponseTest {

  private static final String ERROR = "error";
  private static final String DESCRIPTION = "Not found";
  private static final String ENTITY = "Entity";

  private ExceptionMapperResponse exceptionMapperResponse;

  @Before
  public void setUp() {
    exceptionMapperResponse = new ExceptionMapperResponse();
  }

  @Test
  public void whenCreateBadRequestExceptionMapperThenStatusCodeIsBadRequest() {
    Response codeError = exceptionMapperResponse.createBadRequestExceptionMapper(ERROR, DESCRIPTION,
        ENTITY);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
