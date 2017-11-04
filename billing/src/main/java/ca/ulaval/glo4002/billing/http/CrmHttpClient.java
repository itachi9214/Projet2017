package ca.ulaval.glo4002.billing.http;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;

public class CrmHttpClient {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";

  private Http http;
  private ObjectMapper mapper;

  public CrmHttpClient(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpClient() {
    this(ServiceLocator.getService(Http.class));
  }

  public ClientDto getClientDto(Long clientNumber) throws ClientNotFoundException {
    String url = LOCALHOST + CLIENTS + clientNumber;
    Response response = http.callUrlWithGetMethod(url);

    if (Status.fromStatusCode(response.getStatus()).equals(Status.NOT_FOUND)) {
      throw new ClientNotFoundException(clientNumber);
    }
    ClientDto clientDto = null;
    try {
      clientDto = mapper.readValue(response.readEntity(String.class), ClientDto.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      response.close();
    }
    return clientDto;
  }

}