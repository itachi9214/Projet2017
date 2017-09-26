package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.ClientDto;
import ca.ulaval.glo4002.billing.exception.NotFoundClientException;

public class CrmHttpClient extends HttpClient {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MESSAGE_NOT_FOUND = "client not found";

  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClientDto getClientDtoFromCrm(Long clientNumber) {
    String url = LOCALHOST + CLIENTS + clientNumber;
    Response response = callUrlWithGetMethod(url);

    if (response.getStatus() == HTTP_STATUS_NOT_FOUND)
      throw new NotFoundClientException(MESSAGE_NOT_FOUND);

    ClientDto clientDto = response.readEntity(ClientDto.class);
    response.close();
    return clientDto;
  }

}
