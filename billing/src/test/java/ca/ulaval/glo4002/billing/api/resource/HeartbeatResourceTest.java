package ca.ulaval.glo4002.billing.api.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class HeartbeatResourceTest {

  private static final String TOKEN = "token";

  HeartbeatResource heartbeatResource;

  @Before
  public void setUp() {
    heartbeatResource = new HeartbeatResource();
  }

  @Test
  public void givenTokenWhenBeatThenResponseIsOk() {
    Response response = heartbeatResource.beat(TOKEN);

    assertEquals(response.getStatus(), Response.ok().build().getStatus());
  }

}
