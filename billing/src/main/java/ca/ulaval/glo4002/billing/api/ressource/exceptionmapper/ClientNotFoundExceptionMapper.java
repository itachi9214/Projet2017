package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ClientNotFoundException;

@Provider
public class ClientNotFoundExceptionMapper implements ExceptionMapper<ClientNotFoundException> {

  private ExceptionMapperResponse exceptionMapperResponse;

  public ClientNotFoundExceptionMapper(ExceptionMapperResponse exceptionMapperResponse) {
    this.exceptionMapperResponse = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(ClientNotFoundException clientNotFoundException) {
    return exceptionMapperResponse.createBadRequestExceptionMapper("not found",
        "client " + clientNotFoundException.getClientId() + " not found", "client");
  }

}
