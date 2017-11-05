package ca.ulaval.glo4002.payment.api.resource.exceptionmappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.payment.api.dto.NotFoundDto;

public class ExceptionMapperFactory {

  public Response createNotFoundExceptionMapper() {
    NotFoundDto notFoundDto = new NotFoundDto();
    return Response.status(Status.NOT_FOUND).entity(notFoundDto).type(MediaType.APPLICATION_JSON)
        .build();
  }

}
