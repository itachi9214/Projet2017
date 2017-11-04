package ca.ulaval.glo4002.payment.infrastructure.bill;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.bill.BillDto;
import ca.ulaval.glo4002.payment.http.BillHttpRepository;
import ca.ulaval.glo4002.payment.http.Http;

public class BillingHttpBill implements BillHttpRepository {

  private static final String LOCALHOST = "http://localhost:8080";

  private Http http;
  private ObjectMapper mapper;

  public BillingHttpBill(Http http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public BillingHttpBill() {
    this(ServiceLocator.getService(Http.class));
  }

  @Override
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public BillDto getOldestUnpaidBillForClient(Long clientNumber) {

    return null;
  }

}
