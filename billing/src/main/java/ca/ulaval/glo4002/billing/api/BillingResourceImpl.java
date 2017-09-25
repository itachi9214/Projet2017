package ca.ulaval.glo4002.billing.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.api.dto.ResponseBillDto;
import ca.ulaval.glo4002.billing.domain.BillService;

@Path("/bills")
public class BillingResourceImpl {

  private BillService billService;

  public BillingResourceImpl() {
    billService = new BillService();
  }

  public BillingResourceImpl(BillService billService) {
    this.billService = billService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseBillDto createBill(RequestBillDto requestBillDto) {
    return billService.createBill(requestBillDto);
  }

}
