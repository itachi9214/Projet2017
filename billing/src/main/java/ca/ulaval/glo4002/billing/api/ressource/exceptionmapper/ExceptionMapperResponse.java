package ca.ulaval.glo4002.billing.api.ressource.exceptionmapper;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.billing.api.dto.BadRequestDto;
import ca.ulaval.glo4002.billing.api.dto.BadRequestErrorListDto;
import ca.ulaval.glo4002.billing.api.dto.submission.NotFoundDto;

public class ExceptionMapperResponse {

  public Response createBadRequestExceptionMapper(String error, String description, String entity) {
    List<BadRequestDto> errors = new ArrayList<>();
    BadRequestDto badRequestDto = new BadRequestDto(error, description, entity);
    errors.add(badRequestDto);
    BadRequestErrorListDto badRequestErrorListDto = new BadRequestErrorListDto(errors);

    return Response.status(Status.BAD_REQUEST).entity(badRequestErrorListDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

  public Response createNotFoundExceptionMapper() {
    NotFoundDto notFoundDto = new NotFoundDto();
    return Response.status(Status.NOT_FOUND).entity(notFoundDto).type(MediaType.APPLICATION_JSON)
        .build();
  }

}
