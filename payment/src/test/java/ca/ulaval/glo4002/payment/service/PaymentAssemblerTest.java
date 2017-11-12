package ca.ulaval.glo4002.payment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.identity.Identity;
import ca.ulaval.glo4002.payment.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;

@RunWith(MockitoJUnitRunner.class)
public class PaymentAssemblerTest {

  private static final double FLOAT_PRECISION = 0.001;
  private static final String PAYMENTS = "/payments/";
  private static final long PAYMENT_NUMBER = 1L;
  private static final int AMOUNT = 3;
  private static final long CLIENT_ID = 1L;

  private PaymentAssembler paymentAssembler;
  private RequestPaymentDto requestPaymentDto;

  @Mock
  private IdentityFactory identityFactory;
  @Mock
  private PaymentMethod paymentMethod;
  @Mock
  private Payment payment;
  @Mock
  private Identity identity;

  @Before
  public void setUp() {
    paymentAssembler = new PaymentAssembler(identityFactory);
    requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT, paymentMethod);

    willReturn(PAYMENT_NUMBER).given(identity).getNumber();
    willReturn(identity).given(payment).getPaymentNumber();
  }

  @Test
  public void whenAssemblePaymentFromRequestThenShouldBeTheSame() {
    Payment payment = paymentAssembler.assemblePaymentFromRequest(requestPaymentDto);

    assertEquals(CLIENT_ID, payment.getClientId());
    assertEquals(AMOUNT, payment.getAmount(), FLOAT_PRECISION);
    assertEquals(paymentMethod, payment.getPaymentMethod());
  }

  @Test
  public void whenAssembleResponseFromPaymentThenShouldBeTheSame() {
    ResponsePaymentDto responsePaymentDto = paymentAssembler.assembleResponseFromPayment(payment);

    assertEquals(PAYMENT_NUMBER, responsePaymentDto.getId());
    assertEquals(PAYMENTS + PAYMENT_NUMBER, responsePaymentDto.getUrl());
  }

}
