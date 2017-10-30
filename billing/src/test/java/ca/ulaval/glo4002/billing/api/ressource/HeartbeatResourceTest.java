package ca.ulaval.glo4002.billing.api.ressource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeartbeatResourceTest {

  private static final String TOKEN = "token";

  HeartbeatResource heartbeatResource;

  @Before
  public void setUp() {
    heartbeatResource = new HeartbeatResource();
  }

  @Test
  public void givenTokenWhenBeatThenResponseOk() {
    Response response = heartbeatResource.beat(TOKEN);
    assertEquals(response.getStatus(), Response.ok().build().getStatus());
  }
}
