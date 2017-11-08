package ca.ulaval.glo4002.payment.service;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.domain.payment.ClientRepository;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;
import ca.ulaval.glo4002.payment.http.ClientNotFoundException;
import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

public class PaymentService {

  private PaymentRepository paymentRepository;
  private PaymentAssembler paymentAssembler;
  private BillRepository billRepository;
  private ClientRepository clientRepository;

  public PaymentService() {
    this.paymentAssembler = ServiceLocator.getService(PaymentAssembler.class);
    this.paymentRepository = ServiceLocator.getService(PaymentRepository.class);
    this.billRepository = ServiceLocator.getService(BillRepository.class);
    this.clientRepository = ServiceLocator.getService(ClientRepository.class);
  }

  public PaymentService(PaymentAssembler paymentAssembler, PaymentRepository paymentRepository,
      BillRepository billRepository, ClientRepository clientRepository) {
    this.paymentAssembler = paymentAssembler;
    this.paymentRepository = paymentRepository;
    this.billRepository = billRepository;
    this.clientRepository = clientRepository;
  }

  public ResponsePaymentDto makePayment(RequestPaymentDto requestPaymentDto)
      throws ClientNotFoundException {
    clientRepository.verifyClientExists(requestPaymentDto.getClientId());

    Payment payment = paymentAssembler.assemblePaymentFromRequest(requestPaymentDto);
    paymentRepository.savePayment(payment);

    try {
      float amountLeftAfterPayment = payment.getAmount();
      while (amountLeftAfterPayment > 0) {
        Bill oldestBill = billRepository
            .getOldestUnpaidBillForClient(requestPaymentDto.getClientId());
        float amountToPayForBill = oldestBill.getPriceThatCanBePaid(amountLeftAfterPayment);
        oldestBill.substractPaidAmount(amountToPayForBill);
        amountLeftAfterPayment -= amountToPayForBill;
        billRepository.updateBillAfterPayment(oldestBill);
      }
    } catch (BillNotFoundException exception) {
    }

    ResponsePaymentDto responsePaymentDto = paymentAssembler.assembleResponseFromPayment(payment);
    return responsePaymentDto;
  }

}
