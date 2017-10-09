package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.api.dto.client.ErrorDto;

@Provider
public class ClientNotFoundExceptionMapper implements ExceptionMapper<ClientNotFoundException> {

  @Override
  public Response toResponse(ClientNotFoundException clientNotFoundException) {
    ErrorDto errorDto = new ErrorDto("not found",
        "client " + clientNotFoundException.getClientId() + " not found", "client");
    return Response.status(Status.BAD_REQUEST).entity(errorDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
