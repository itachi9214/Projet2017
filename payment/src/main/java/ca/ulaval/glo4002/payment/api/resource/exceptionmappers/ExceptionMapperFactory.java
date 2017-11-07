package ca.ulaval.glo4002.payment.api.resource.exceptionmappers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.payment.api.dto.BadRequestDto;
import ca.ulaval.glo4002.payment.api.dto.BadRequestErrorListDto;

public class ExceptionMapperFactory {

  public Response createBadRequestExceptionMapper(String error, String description, String entity) {
    List<BadRequestDto> errors = new ArrayList<>();
    BadRequestDto badRequestDto = new BadRequestDto(error, description, entity);
    errors.add(badRequestDto);
    BadRequestErrorListDto badRequestErrorListDto = new BadRequestErrorListDto(errors);

    return Response.status(Status.BAD_REQUEST).entity(badRequestErrorListDto)
        .type(MediaType.APPLICATION_JSON).build();
  }

}
