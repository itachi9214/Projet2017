package ca.ulaval.glo4002.payment.service;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.api.dto.ResponsePaymentDto;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.domain.payment.ClientRepository;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;
import ca.ulaval.glo4002.payment.domain.payment.PaymentSource;
import ca.ulaval.glo4002.payment.http.ClientNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  private static final PaymentSource SOURCE = PaymentSource.CREDIT_CARD;
  private static final String ACCOUNT = "XXXX-XXX-XXX";
  private static final long CLIENT_ID = 1L;
  private static final long BILL_ID = 10L;
  private static String URL;
  private static final String BASE_URL = "/payments/";
  private static final float AMOUNT = 50;
  private static final int EXACT_REMAINING_AMOUNT = 50;
  private static final int REMAINING_AMOUNT = 80;

  private PaymentService paymentService;
  private RequestPaymentDto requestPaymentDto;
  private ResponsePaymentDto responsePaymentDto;

  @Mock
  private PaymentAssembler paymentAssembler;
  @Mock
  private PaymentRepository paymentRepository;
  @Mock
  private BillRepository billRepository;
  @Mock
  private ClientRepository clientRepository;
  @Mock
  private Payment payment;

  private Bill bill;

  @Before
  public void setUp() {
    paymentService = new PaymentService(paymentAssembler, paymentRepository, billRepository,
        clientRepository);
    requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT,
        new PaymentMethod(ACCOUNT, SOURCE));
    URL = BASE_URL + BILL_ID;
    responsePaymentDto = new ResponsePaymentDto(BILL_ID, URL);
    bill = new Bill(BILL_ID, new BigDecimal(EXACT_REMAINING_AMOUNT));

    willReturn(AMOUNT).given(payment).getAmount();
    willReturn(payment).given(paymentAssembler).assemblePaymentFromRequest(requestPaymentDto);
    willReturn(bill).given(billRepository).getOldestUnpaidBillForClient(CLIENT_ID);
    willReturn(responsePaymentDto).given(paymentAssembler).assembleResponseFromPayment(payment);
  }

  @Test(expected = ClientNotFoundException.class)
  public void givenUnexistingClientWhenMakePaymentThenThrowsException() {
    willThrow(ClientNotFoundException.class).given(clientRepository).verifyClientExists(CLIENT_ID);

    paymentService.makePayment(requestPaymentDto);
  }

  @Test
  public void whenMakePaymentThenVerifyPaymentIsSaved() {
    paymentService.makePayment(requestPaymentDto);

    verify(paymentRepository).savePayment(payment);
  }

}