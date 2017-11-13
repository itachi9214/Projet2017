package ca.ulaval.glo4002.payment.api.resource.exceptionmappers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.http.ClientNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ClientNotFoundExceptionMapperTest {

  private static final long CLIENT_ID = 2L;
  private static final String NOT_FOUND = "not found";
  private static final String CLIENT_ENTITY = "client";
  private static final String DESCRIPTION = "client 2 not found";

  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;
  private ClientNotFoundException clientNotFoundException;

  @Mock
  ExceptionMapperFactory exceptionMapperFactory;

  @Before
  public void setUp() {
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper(exceptionMapperFactory);
    clientNotFoundException = new ClientNotFoundException(CLIENT_ID);
  }

  @Test
  public void givenclientNotFoundExceptionWhenToResponseThenVerifyCreateBadResquestExceptionMapperIsCalled() {
    clientNotFoundExceptionMapper.toResponse(clientNotFoundException);

    verify(exceptionMapperFactory).createBadRequestExceptionMapper(NOT_FOUND, DESCRIPTION,
        CLIENT_ENTITY);
  }

}
