package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

public class BadRequestExceptionMapperTest {

  private BadRequestExceptionMapper badRequestExceptionMapper;
  private static final String ERROR = "error";
  private static final String DESCRIPTION = "Not found";
  private static final String ENTITY = "Entity";

  @Before
  public void setUp() {
    badRequestExceptionMapper = new BadRequestExceptionMapper();
  }

  @Test
  public void givenBadRequestDetectedWhenToReponseThenStatuscodeIsBadRequest() {
    Response codeError = badRequestExceptionMapper.CreateBadRequestExceptionMapper(ERROR,
        DESCRIPTION, ENTITY);

    assertEquals(codeError.getStatus(), Status.BAD_REQUEST.getStatusCode());
  }

}
