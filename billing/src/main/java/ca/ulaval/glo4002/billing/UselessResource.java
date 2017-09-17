package ca.ulaval.glo4002.billing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/useless")
public class UselessResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public UselessDto checkingIfUseless() {
    UselessDto uselessDto = new UselessDto();
    return uselessDto;
  }

}
