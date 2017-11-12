package ca.ulaval.glo4002.payment.api.resource.exceptionmappers;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.http.ClientNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ClientNotFoundExceptionMapperTest {

  private static final long ID_CLIENT = 2L;
  private static final String NOT_FOUND = "not found";
  private static final String CLIENT = "client";
  private static final String DESCRIPTION = "client 2 not found";

  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;
  private ClientNotFoundException clientNotFoundException;

  @Mock
  ExceptionMapperFactory exceptionMapperFactory;

  @Before
  public void setUp() {
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper(exceptionMapperFactory);
    clientNotFoundException = new ClientNotFoundException(ID_CLIENT);
  }

  @Test
  public void whenToResponseThenVerifyCreateBadResquestExceptionMapperIsCalled() {
    clientNotFoundExceptionMapper.toResponse(clientNotFoundException);

    verify(exceptionMapperFactory).createBadRequestExceptionMapper(NOT_FOUND, DESCRIPTION, CLIENT);
  }

}
