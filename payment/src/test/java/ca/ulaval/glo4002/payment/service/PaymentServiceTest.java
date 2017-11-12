package ca.ulaval.glo4002.payment.service;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  private static final int ZERO = 0;
  private static final PaymentSource SOURCE = PaymentSource.CREDIT_CARD;
  private static final String ACCOUNT = "XXXX-XXX-XXX";
  private static final long CLIENT_ID = 1L;
  private static final long BILL_ID = 10L;
  private static final String BASE_URL = "/payments/";
  private static final String URL = BASE_URL + BILL_ID;
  private static final float AMOUNT = 50;
  private static final int EXACT_REMAINING_AMOUNT = 50;

  private PaymentService paymentService;
  private RequestPaymentDto requestPaymentDto;
  private ResponsePaymentDto responsePaymentDto;
  private Bill bill;

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
  @Mock
  private Bill oldestBill;

  @Before
  public void setUp() {
    paymentService = new PaymentService(paymentAssembler, paymentRepository, billRepository,
        clientRepository);
    requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT,
        new PaymentMethod(ACCOUNT, SOURCE));
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

  @Test
  public void givenUnexistantBillWhenMakePaymentThenVerifyUpdateBillAfterPaymentIsNotCalled() {
    willThrow(BillNotFoundException.class).given(billRepository)
        .getOldestUnpaidBillForClient(CLIENT_ID);

    paymentService.makePayment(requestPaymentDto);

    verify(billRepository, times(ZERO)).updateBillAfterPayment(bill);
  }

}
