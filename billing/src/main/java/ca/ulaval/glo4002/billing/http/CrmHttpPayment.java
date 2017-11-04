package ca.ulaval.glo4002.billing.http;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;

public class CrmHttpPayment {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String PAYMENTS = "/payments/";

  private ObjectMapper mapper;
  private Http http;

  public CrmHttpPayment(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpPayment() {
    this(ServiceLocator.getService(Http.class));
  }

  public void makePayment(RequestPaymentDto requestPaymentDto) {
    String url = LOCALHOST + PAYMENTS;
    Response response = http.callUrlWithGetMethod(url);
    response.close();
  }

}
