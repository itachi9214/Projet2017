package ca.ulaval.glo4002.billing.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.time.Instant;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;

@RunWith(MockitoJUnitRunner.class)
public class CrmHttpClientTest {

  private static final String A_STRING = "name";
  private static final Long NON_EXISTING_CLIENT_NUMBER = -3L;
  private static final Long EXISTING_CLIENT_NUMBER = 1L;

  private CrmHttpClient crmHttpClient;
  private ClientDto clientDto;
  private ObjectMapper mapper;

  @Mock
  private Http http;
  @Mock
  private Response response;

  @Before
  public void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    clientDto = new ClientDto(EXISTING_CLIENT_NUMBER, Instant.now(), DueTerm.IMMEDIATE, A_STRING);
    willReturn(response).given(http).callUrlWithGetMethod(anyString());
    crmHttpClient = new CrmHttpClient(http);
  }

  @Test(expected = ClientNotFoundException.class)
  public void givenClientNumberNotFoundWhenGetClientDtoThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpClient.getClientDto(NON_EXISTING_CLIENT_NUMBER);
  }

  @Test
  public void givenClientNumberFoundWhenGetClientDtoThenReturnDto() throws IOException {
    willReturn(Status.OK.getStatusCode()).given(response).getStatus();
    willReturn(mapper.writeValueAsString(clientDto)).given(response).readEntity(String.class);

    ClientDto result = crmHttpClient.getClientDto(EXISTING_CLIENT_NUMBER);

    assertTrue(result instanceof ClientDto);
  }

}