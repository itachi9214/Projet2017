package ca.ulaval.glo4002.billing.domain;

import ca.ulaval.glo4002.billing.api.CrmHttpClient;
import ca.ulaval.glo4002.billing.api.dto.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.api.dto.ResponseBillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.infrastructure.BillInMemory;

public class BillService {

  private BillAssembler billAssembler;
  private BillRepository billRepository;
  private CrmHttpClient crmHttpClient;

  public BillService() {
    billAssembler = new BillAssembler();
    billRepository = new BillInMemory();
    crmHttpClient = new CrmHttpClient();
  }

  public BillService(BillAssembler billAssembler, BillRepository billRepository) {
    this.billAssembler = billAssembler;
    this.billRepository = billRepository;
  }

  public ResponseBillDto createBill(RequestBillDto requestBillDto) {

    Bill bill = billAssembler.create(requestBillDto);
    billRepository.createBill(bill);
    return billAssembler.create(bill);

  }

  public ClientDto getClientByIdInCrm(Long clientId) {
    ClientDto clientDto = crmHttpClient.getClientDtoFromCrm(clientId);
    return clientDto;
  }

}
