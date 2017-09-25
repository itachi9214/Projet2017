package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface HeartbeatResourceInterface {
  public Response beat(@QueryParam("token") String token);

}
