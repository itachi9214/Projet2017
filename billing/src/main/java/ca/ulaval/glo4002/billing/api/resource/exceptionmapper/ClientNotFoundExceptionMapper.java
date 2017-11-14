package ca.ulaval.glo4002.billing.api.resource.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.http.ClientNotFoundException;

@Provider
public class ClientNotFoundExceptionMapper implements ExceptionMapper<ClientNotFoundException> {

  private static final String SPACE = " ";
  private static final String NOT_FOUND = "not found";
  private static final String CLIENT = "client";

  private ExceptionMapperFactory exceptionMapperFactory;

  public ClientNotFoundExceptionMapper() {
    this.exceptionMapperFactory = new ExceptionMapperFactory();
  }

  public ClientNotFoundExceptionMapper(ExceptionMapperFactory exceptionMapperResponse) {
    this.exceptionMapperFactory = exceptionMapperResponse;
  }

  @Override
  public Response toResponse(ClientNotFoundException clientNotFoundException) {
    return exceptionMapperFactory.createBadRequestExceptionMapper(NOT_FOUND,
        CLIENT + SPACE + clientNotFoundException.getClientId() + SPACE + NOT_FOUND, CLIENT);
  }

}
