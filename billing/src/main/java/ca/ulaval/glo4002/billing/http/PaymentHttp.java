package ca.ulaval.glo4002.billing.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;

public class PaymentHttp {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String PAYMENTS = "/payments/";

  private ObjectMapper mapper;
  private Http http;

  public PaymentHttp(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public PaymentHttp() {
    this(ServiceLocator.getService(Http.class));
  }

  public void makePayment(RequestPaymentDto requestPaymentDto) {
    String url = LOCALHOST + PAYMENTS;
    http.callUrlWithPostMethod(url, requestPaymentDto);
  }

}
