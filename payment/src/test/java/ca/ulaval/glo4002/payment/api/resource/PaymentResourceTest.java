package ca.ulaval.glo4002.payment.api.resource;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;
import ca.ulaval.glo4002.payment.domain.payment.PaymentSource;
import ca.ulaval.glo4002.payment.service.PaymentService;

@RunWith(MockitoJUnitRunner.class)
public class PaymentResourceTest {

  private static final PaymentSource SOURCE = PaymentSource.CREDIT_CARD;
  private static final String ACCOUNT = "XXXX-XXX-XXX";
  private static final float AMOUNT = 50;
  private static final long CLIENT_ID = 1L;

  private PaymentResource paymentResource;

  @Mock
  private PaymentService paymentService;

  @Before
  public void setUp() {
    paymentResource = new PaymentResource(paymentService);
  }

  @Test
  public void whenMakePaymentThenVerifyPaymentIsMade() {
    RequestPaymentDto requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT,
        new PaymentMethod(ACCOUNT, SOURCE));

    paymentResource.makePayment(requestPaymentDto);

    verify(paymentService).makePayment(requestPaymentDto);
  }

}
