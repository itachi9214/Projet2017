package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.http.ClientRepository;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.Http;

public class CrmHttpClient implements ClientRepository {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";
  Http http;
  private ObjectMapper mapper;

  public CrmHttpClient(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpClient() {

  }

  @Override
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
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