package ca.ulaval.glo4002.payment.http;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.payment.ServiceLocator;

public class CrmHttpClient {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";

  private UtilHttp http;
  private ObjectMapper mapper;

  public CrmHttpClient(UtilHttp http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpClient() {
    this(ServiceLocator.getService(UtilHttp.class));
  }

  public void verifyClientExists(Long clientNumber) throws ClientNotFoundException {
    String url = LOCALHOST + CLIENTS + clientNumber;
    Response response = http.callUrlWithGetMethod(url);

    if (Status.fromStatusCode(response.getStatus()).equals(Status.NOT_FOUND)) {
      throw new ClientNotFoundException(clientNumber);
    }
  }

}
