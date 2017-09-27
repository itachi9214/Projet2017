package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.api.dto.ResponseBillDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillAssembler;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.http.CrmHttpClient;
import ca.ulaval.glo4002.billing.http.HttpClient;
import ca.ulaval.glo4002.billing.infrastructure.BillInMemory;

public class BillService {

  private BillAssembler billAssembler;
  private BillRepository billRepository;
  private HttpClient httpClient;

  public BillService() {
    billAssembler = new BillAssembler();
    billRepository = new BillInMemory();
    httpClient = new CrmHttpClient();
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
    ClientDto clientDto = httpClient.getClientDto(clientId);
    return clientDto;
  }

}
