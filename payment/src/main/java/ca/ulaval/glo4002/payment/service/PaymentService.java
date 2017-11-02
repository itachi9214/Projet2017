package ca.ulaval.glo4002.payment.service;

import java.util.List;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillState;
import ca.ulaval.glo4002.payment.domain.payment.BillRepository;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;

public class PaymentService {

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
    Bill oldestBill = getOldestUnpaidBillForClient(requestPaymentDto.getClientId());
    Payment payment = paymentAssembler.toDomain(requestPaymentDto);

    oldestBill.addPaymentAndUpdateState(payment.getAmount());
    paymentRepository.savePayment(payment);
    if (oldestBill.getState().equals(BillState.PAID)) {
      billRepository.saveBillStateToPaid(oldestBill);
    }

    ResponsePaymentDto responsePaymentDto = paymentAssembler.toDto(payment);
    return responsePaymentDto;
  }

  private Bill getOldestUnpaidBillForClient(Long clientId) {
    List<Bill> unpaidBills = billRepository.getUnpaidBillsOrderedByOldestForClient(clientId);
    Bill oldestBill = null;
    if (!unpaidBills.isEmpty()) {
      oldestBill = unpaidBills.get(0);
    }
    return oldestBill;
  }

}
