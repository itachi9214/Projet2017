package ca.ulaval.glo4002.billing.api.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class HeartbeatResourceIT extends JerseyTest {

  private static final Object A_TOKEN = "token";

  @Override
  public Application configure() {
    return new ResourceConfig().register(new HeartbeatResource());
  }

  @Test
  public void whenBeatThenResponseStatusIsOk() {
    Response response = target("/heartbeat").queryParam("token", A_TOKEN).request().get();

    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }

}
