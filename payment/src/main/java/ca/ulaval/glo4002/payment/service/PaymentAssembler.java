package ca.ulaval.glo4002.payment.service;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.payment.domain.payment.Payment;

public class PaymentAssembler {

  private static final String BASE_URL = "/payments/";

  private IdentityFactory identityFactory;

  public PaymentAssembler() {
    this.identityFactory = ServiceLocator.getService(IdentityFactory.class);
  }

  public PaymentAssembler(IdentityFactory identityFactory) {
    this.identityFactory = identityFactory;
  }

  public Payment assemblePaymentFromRequest(RequestPaymentDto requestPaymentDto) {
    Payment payment = new Payment(identityFactory.createId(), requestPaymentDto.getAmount(),
        requestPaymentDto.getClientId(), requestPaymentDto.getPaymentMethod());
    return payment;
  }

  public ResponsePaymentDto assembleResponseFromPayment(Payment payment) {
    ResponsePaymentDto responsePaymentDto = new ResponsePaymentDto(
        payment.getPaymentNumber().getNumber(), BASE_URL + payment.getPaymentNumber().getNumber());
    return responsePaymentDto;
  }

}
