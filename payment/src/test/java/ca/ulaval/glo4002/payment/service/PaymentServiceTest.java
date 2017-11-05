package ca.ulaval.glo4002.payment.service;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.api.dto.RequestPaymentDto;
import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.domain.bill.BillRepository;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;
import ca.ulaval.glo4002.payment.domain.payment.PaymentSource;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  private static final PaymentSource SOURCE = PaymentSource.CREDIT_CARD;
  private static final String ACCOUNT = "XXXX-XXX-XXX";
  private static final long CLIENT_ID = 1L;
  private static final float AMOUNT = 50;

  private PaymentService paymentService;
  private RequestPaymentDto requestPaymentDto;

  @Mock
  private PaymentAssembler paymentAssembler;
  @Mock
  private PaymentRepository paymentRepository;
  @Mock
  private BillRepository billRepository;
  @Mock
  private Payment payment;
  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    paymentService = new PaymentService(paymentAssembler, paymentRepository, billRepository);
    requestPaymentDto = new RequestPaymentDto(CLIENT_ID, AMOUNT,
        new PaymentMethod(ACCOUNT, SOURCE));

    willReturn(AMOUNT).given(payment).getAmount();
    willReturn(payment).given(paymentAssembler).assemblePaymentFromRequest(requestPaymentDto);
    willReturn(bill).given(billRepository).getOldestUnpaidBillForClient(CLIENT_ID);
  }

  @Test
  public void whenMakePaymentThenVerifyPaymentIsSaved() {
    paymentService.makePayment(requestPaymentDto);

    verify(paymentRepository).savePayment(payment);
  }

  @Test
  public void whenMakePaymentThenVerifyUnpaidBillsAreFound() {
    paymentService.makePayment(requestPaymentDto);

    verify(billRepository).getOldestUnpaidBillForClient(CLIENT_ID);
  }

  @Test
  public void whenMakePaymentThenVerifyPaymentIsAddedToBill() {
    paymentService.makePayment(requestPaymentDto);

    verify(bill).addPayment(payment.getAmount());
  }

  @Test
  public void whenMakePaymentThenVerifyBillIsUpdated() {
    paymentService.makePayment(requestPaymentDto);

    verify(billRepository).updateBillAfterPayment(bill);
  }

}