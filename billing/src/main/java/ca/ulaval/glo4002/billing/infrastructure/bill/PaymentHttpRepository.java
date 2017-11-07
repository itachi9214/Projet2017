package ca.ulaval.glo4002.billing.infrastructure.bill;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.PaymentRepository;
import ca.ulaval.glo4002.billing.http.PaymentHttp;

public class PaymentHttpRepository implements PaymentRepository {

  private PaymentHttp paymentHttp;

  public PaymentHttpRepository() {
    this(ServiceLocator.getService(PaymentHttp.class));
  }

  public PaymentHttpRepository(PaymentHttp paymentHttp) {
    this.paymentHttp = paymentHttp;
  }

  @Override
  public void savePayment(RequestPaymentDto requestPaymentDto) {
    paymentHttp.makePayment(requestPaymentDto);
  }

}
