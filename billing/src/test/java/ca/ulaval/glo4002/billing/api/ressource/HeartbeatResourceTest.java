package ca.ulaval.glo4002.billing.api.ressource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.ressource.HeartbeatResource;

@RunWith(MockitoJUnitRunner.class)
public class HeartbeatResourceTest extends JerseyTest {

  private static final Object A_TOKEN = "token";

  @Override
  public Application configure() {
    MockitoAnnotations.initMocks(this);

    return new ResourceConfig().register(new HeartbeatResource());
  }

  @Test
  public void whenBeatThenResponseStatusIsOk() {
    Response response = target("/heartbeat").queryParam("token", A_TOKEN).request().get();

    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }

}
