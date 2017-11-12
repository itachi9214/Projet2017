package ca.ulaval.glo4002.billing.infrastructure.bill;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.payment.PaymentSource;
import ca.ulaval.glo4002.billing.http.PaymentHttp;

@RunWith(MockitoJUnitRunner.class)
public class PaymentHttpRepositoryTest {

  private static final Long CLIENT_ID = 1L;
  private static final float AMOUNT = 20.0f;
  private static final String ACCOUNT = "XXXX-XXX-XXX";
  private static final PaymentSource SOURCE = PaymentSource.CHECK;

  private PaymentHttpRepository paymentHttpRepository;
  private RequestPaymentDto requestPaymentDto;

  @Mock
  private PaymentHttp paymentHttp;

  @Before
  public void setup() {
    requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT, ACCOUNT, SOURCE);
    paymentHttpRepository = new PaymentHttpRepository(paymentHttp);
  }

  @Test
  public void givenRequestPaymentDtoWhenSavePaymentThenMakePayment() {
    paymentHttpRepository.savePayment(requestPaymentDto);

    verify(paymentHttp).makePayment(requestPaymentDto);
  }

}
