package ca.ulaval.glo4002.billing.domain.bill;

import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;

public interface PaymentHttpRepository {

  public void savePayment(RequestPaymentDto requestPaymentDto);

}
