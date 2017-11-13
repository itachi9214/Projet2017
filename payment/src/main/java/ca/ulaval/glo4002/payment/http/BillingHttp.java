package ca.ulaval.glo4002.payment.http;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

public class BillingHttp {

  private static final String LOCALHOST = "http://localhost:8181";
  private static final String BILLS = "/bills/";
  private static final String UPDATE = "update/";

  private UtilHttp utilHttp;
  private ObjectMapper mapper;

  public BillingHttp() {
    this(ServiceLocator.getService(UtilHttp.class));
  }

  public BillingHttp(UtilHttp http) {
    this.utilHttp = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public Bill getOldestUnpaidBillForClient(Long clientNumber) throws BillNotFoundException {
    String url = LOCALHOST + BILLS + clientNumber;
    Response response = utilHttp.callUrlWithGetMethod(url);

    Bill bill = extractBillFromResponse(response);
    return bill;
  }

  private Bill extractBillFromResponse(Response response) throws BillNotFoundException {
    Bill bill = null;
    try {
      bill = mapper.readValue(response.readEntity(String.class), Bill.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      response.close();
    }

    if (Status.NOT_FOUND.getStatusCode() == response.getStatus()) {
      throw new BillNotFoundException();
    }
    return bill;
  }

  public void updateBillAfterPayment(Bill bill) {
    String url = LOCALHOST + BILLS + UPDATE;
    utilHttp.callUrlWithPostMethod(url, bill);
  }

}
