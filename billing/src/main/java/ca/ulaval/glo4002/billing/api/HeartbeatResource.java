package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.api.dto.HeartbeatDto;

@Path("/heartbeat")
public class HeartbeatResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public HeartbeatDto beat(@QueryParam("token") String token) {
    return new HeartbeatDto(token);
  }

}
