package ca.ulaval.glo4002.payment.http;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.domain.bill.Bill;

public class BillingHttp {

  private static final String LOCALHOST = "http://localhost:8181";
  private static final String BILLS = "/bills/";

  private Http http;
  private ObjectMapper mapper;

  public BillingHttp() {
    this(ServiceLocator.getService(Http.class));
  }

  public BillingHttp(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public Bill getOldestUnpaidBillForClient(Long clientNumber) {
    String url = LOCALHOST + BILLS + clientNumber;
    Response response = http.callUrlWithGetMethod(url);

    Bill billDto = null;
    try {
      billDto = mapper.readValue(response.readEntity(String.class), Bill.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      response.close();
    }
    return billDto;
  }

  public void saveBillStateToPaid(Bill bill) {
    String url = LOCALHOST + BILLS;
    http.callUrlWithPostMethod(url, bill);
  }

}
