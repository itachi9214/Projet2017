package ca.ulaval.glo4002.billing.infrastructure.bill;

import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.PaymentHttpRepository;
import ca.ulaval.glo4002.billing.http.PaymentHttp;

public class PaymentHttpInMemoryRepository implements PaymentHttpRepository {

  private PaymentHttp paymentHttp;

  @Override
  public void savePayment(RequestPaymentDto requestPaymentDto) {
    paymentHttp.makePayment(requestPaymentDto);
  }

}
