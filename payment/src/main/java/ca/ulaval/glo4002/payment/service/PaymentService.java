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

  private static final int AMOUNT_LEFT_NULL = 0;
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
      payBillsForClientWithPayment(requestPaymentDto.getClientId(), payment);
    } catch (BillNotFoundException exception) {
    }

    ResponsePaymentDto responsePaymentDto = paymentAssembler.assembleResponseFromPayment(payment);
    return responsePaymentDto;
  }

  private void payBillsForClientWithPayment(Long clientId, Payment payment) {
    float amountLeftAfterPayment = payment.getAmount();

    while (amountLeftAfterPayment > AMOUNT_LEFT_NULL) {
      Bill oldestBill = billRepository.getOldestUnpaidBillForClient(clientId);
      float amountToPayForBill = oldestBill.getPriceThatCanBePaid(amountLeftAfterPayment);
      payBillWithAmount(oldestBill, amountToPayForBill);
      amountLeftAfterPayment -= amountToPayForBill;
    }
  }

  private void payBillWithAmount(Bill oldestBill, float amountToPayForBill) {
    oldestBill.substractPaidAmount(amountToPayForBill);
    billRepository.updateBillAfterPayment(oldestBill);
  }

}
