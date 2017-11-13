package ca.ulaval.glo4002.billing.domain.bill;

import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;

public interface PaymentRepository {

  public void savePayment(RequestPaymentDto requestPaymentDto);

}
