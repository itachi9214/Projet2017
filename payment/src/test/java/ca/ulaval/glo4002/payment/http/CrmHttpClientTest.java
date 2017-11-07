package ca.ulaval.glo4002.payment.http;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

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

@RunWith(MockitoJUnitRunner.class)
public class CrmHttpClientTest {

  private static final String A_STRING = "name";
  private static final Long NON_EXISTING_CLIENT_NUMBER = -3L;
  private static final Long EXISTING_CLIENT_NUMBER = 1L;

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
    crmHttpClient = new CrmHttpClient(http);
  }

  @Test(expected = ClientNotFoundException.class)
  public void givenClientNumberNotFoundWhenVerifyClientExistsThenThrowException() {
    willReturn(Status.NOT_FOUND.getStatusCode()).given(response).getStatus();

    crmHttpClient.verifyClientExists(NON_EXISTING_CLIENT_NUMBER);
  }

}