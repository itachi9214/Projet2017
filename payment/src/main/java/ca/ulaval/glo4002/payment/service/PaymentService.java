package ca.ulaval.glo4002.payment.service;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.domain.bill.BillState;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;

public class PaymentService {

  private static final int OLDEST_BILL_INDEX = 0;

  private PaymentRepository paymentRepository;
  private PaymentAssembler paymentAssembler;
  private BillRepository billRepository;

  public PaymentService() {
    this.paymentAssembler = ServiceLocator.getService(PaymentAssembler.class);
    this.paymentRepository = ServiceLocator.getService(PaymentRepository.class);
    this.billRepository = ServiceLocator.getService(BillRepository.class);
  }

  public PaymentService(PaymentAssembler paymentAssembler, PaymentRepository paymentRepository,
      BillRepository billRepository) {
    this.paymentAssembler = paymentAssembler;
    this.paymentRepository = paymentRepository;
    this.billRepository = billRepository;
  }

  public ResponsePaymentDto makePayment(RequestPaymentDto requestPaymentDto) {
    Bill oldestBill = billRepository.getOldestUnpaidBillForClient(requestPaymentDto.getClientId());
    Payment payment = paymentAssembler.toDomain(requestPaymentDto);

    oldestBill.addPaymentAndUpdateState(payment.getAmount());
    paymentRepository.savePayment(payment);
    if (oldestBill.getBillState().equals(BillState.PAID)) {
      billRepository.changeBillStateToPaid(oldestBill);
    }

    ResponsePaymentDto responsePaymentDto = paymentAssembler.toDto(payment);
    return responsePaymentDto;
  }

}
