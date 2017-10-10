package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ClientNotFoundException;

@Provider
public class ClientNotFoundExceptionMapper extends ExceptionMapperResponse
    implements ExceptionMapper<ClientNotFoundException> {

  @Override
  public Response toResponse(ClientNotFoundException clientNotFoundException) {
    return createBadRequestExceptionMapper("not found",
        "client " + clientNotFoundException.getClientId() + " not found", "client");
  }

}
