package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.billing.api.dto.BadRequestDto;

public class BadRequestExceptionMapper {

  public Response CreateBadRequestExceptionMapper(String error, String description, String entity) {
    BadRequestDto badRequestDto = new BadRequestDto(error, description, entity);
    return Response.status(Status.BAD_REQUEST).entity(badRequestDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
