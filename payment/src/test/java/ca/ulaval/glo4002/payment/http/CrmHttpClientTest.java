package ca.ulaval.glo4002.payment.http;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(MockitoJUnitRunner.class)
public class CrmHttpClientTest {

  private static final Long CLIENT_NUMBER = 1L;

  private CrmHttpClient crmHttpClient;
  private ObjectMapper mapper;

  @Mock
  private UtilHttp http;
  @Mock
  private Response response;

  @Before
  public void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    willReturn(response).given(http).callUrlWithGetMethod(anyString());
    willReturn(Status.OK.getStatusCode()).given(response).getStatus();
    crmHttpClient = new CrmHttpClient(http);
  }

  @Test(expected = ClientNotFoundException.class)
  public void givenClientNumberNotFoundWhenVerifyClientExistsThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpClient.verifyClientExists(CLIENT_NUMBER);
  }

  @Test
  public void whenVerifyClientExistsThenGetIsCalled() throws JsonProcessingException {
    crmHttpClient.verifyClientExists(CLIENT_NUMBER);

    verify(http).callUrlWithGetMethod(anyString());
  }

}