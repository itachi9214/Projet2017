package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.api.dto.HeartbeatDto;

@Path("/heartbeat")
public class HeartbeatResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response beat(@QueryParam("token") String token) {
    return Response.ok(new HeartbeatDto(token)).build();
  }

}
