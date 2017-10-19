package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.http.ClientNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ClientNotFoundExceptionMapperTest {

  private static final long ID_CLIENT = 1L;

  private ClientNotFoundExceptionMapper clientNotFoundExceptionMapper;

  @Mock
  private ExceptionMapperResponse exceptionMapperResponse;
  @Mock
  private ClientNotFoundException clientNotFoundException;

  @Before
  public void setUp() {
    clientNotFoundExceptionMapper = new ClientNotFoundExceptionMapper(exceptionMapperResponse);
    clientNotFoundException = new ClientNotFoundException(ID_CLIENT);
  }

  @Test
  public void givenClientNotFoundExceptionWhenToResponseThenStatusCodeIsBadRequest() {
    clientNotFoundExceptionMapper.toResponse(clientNotFoundException);

    verify(exceptionMapperResponse).createBadRequestExceptionMapper(anyString(), anyString(),
        anyString());
  }

}
