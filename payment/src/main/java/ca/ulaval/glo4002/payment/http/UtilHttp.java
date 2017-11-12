
package ca.ulaval.glo4002.payment.http;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

public class UtilHttp {

  private static final int TIMEOUT = 2000;

  public Response callUrlWithGetMethod(String url) {
    Client client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT);
    client.property(ClientProperties.READ_TIMEOUT, TIMEOUT);
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON_TYPE).get();
  }

  public Response callUrlWithPostMethod(String url, Object object) {
    Client client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT);
    client.property(ClientProperties.READ_TIMEOUT, TIMEOUT);
    Entity<Object> entity = Entity.entity(object, MediaType.APPLICATION_JSON);
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
  }

}