package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.api.dto.client.ErrorDto;
import ca.ulaval.glo4002.billing.infrastructure.bill.BillAlreadyExistsException;

@Provider
public class BillAlreadyExistsExceptionMapper implements ExceptionMapper<BillAlreadyExistsException> {

	@Override
	public Response toResponse(BillAlreadyExistsException billAlreadyExistsException) {
		ErrorDto errorDto = new ErrorDto("wrong status", "Invoice already accepted","invoice");
		return Response.status(Status.BAD_REQUEST).entity(errorDto)
		        .type(MediaType.APPLICATION_JSON).build();
	}

}
