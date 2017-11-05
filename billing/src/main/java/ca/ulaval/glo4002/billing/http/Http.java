package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

<<<<<<< HEAD
=======
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;

>>>>>>> 7aa67f3dd174a088f3cd71547df5c296cf3bbe10
public class Http {

  public Response callUrlWithGetMethod(String url) {
    Client client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, 2000);
    client.property(ClientProperties.READ_TIMEOUT, 2000);
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON_TYPE).get();
  }

<<<<<<< HEAD
  public void callUrlWithPostMethod(String url, Object object) {
    Entity<Object> entity = Entity.entity(object, MediaType.APPLICATION_JSON);
=======
  public void callUrlWithPostMethod(String url, RequestPaymentDto requestPaymentDto) {
    Entity<RequestPaymentDto> entity = Entity.entity(requestPaymentDto, MediaType.APPLICATION_JSON);
>>>>>>> 7aa67f3dd174a088f3cd71547df5c296cf3bbe10
    Client client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, 2000);
    client.property(ClientProperties.READ_TIMEOUT, 2000);
    WebTarget target = client.target(url);
    target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
  }

}