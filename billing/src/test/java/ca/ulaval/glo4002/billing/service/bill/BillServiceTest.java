package ca.ulaval.glo4002.billing.service.bill;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.BillState;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  private static final long BILL_NUMBER = 10L;
  private static final long CLIENT_ID = 100L;
  private static final int PRICE = 10;
  private static final BigDecimal TOTAL_PRICE = new BigDecimal(PRICE);
  private static final BigDecimal PAID_PRICE = new BigDecimal(PRICE);

  private BillService billService;
  private Identity identity;
  private BillForPaymentDto billForPaymentDto;

  @Mock
  private Submission submission;
  @Mock
  private BillRepository billRepository;
  @Mock
  private Bill bill;
  @Mock
  private IdentityFactory identityFactory;
  @Mock
  private BillAssembler billAssembler;
  @Mock
  private SubmissionRepository submissionRepository;

  @Before
  public void setUp() {
    billService = new BillService(identityFactory, billAssembler, submissionRepository,
        billRepository);
    identity = new Identity(BILL_NUMBER);
    billForPaymentDto = new BillForPaymentDto(BILL_NUMBER, CLIENT_ID, TOTAL_PRICE, PAID_PRICE,
        BillState.UNPAID);
  }

  @Test
  public void whenCreateBillThenVerifySubmissionIsFound() {
    willReturn(identity).given(identityFactory).createIdFromNumber(BILL_NUMBER);

    billService.createBill(BILL_NUMBER);

    verify(submissionRepository).findSubmissionById(identity);
  }

  @Test
  public void whenCreateBillThenVerifyBillIsCreatedByRepository() {
    willReturn(identity).given(identityFactory).createIdFromNumber(BILL_NUMBER);
    willReturn(submission).given(submissionRepository).findSubmissionById(identity);
    willReturn(bill).given(billAssembler).createBillFromSubmission(submission);

    billService.createBill(BILL_NUMBER);

    verify(billRepository).createBill(bill);
  }

  @Test
  public void whenGetOldestUnpaidBillForClientThenVerifyFindOldestUnpaidBillByClient() {
    billService.getOldestUnpaidBillForClient(CLIENT_ID);

    verify(billRepository).findOldestUnpaidBillByClientId(CLIENT_ID);
  }

  @Test
  public void whenGetOldestUnpaidBillForClientThenCreateBillForPaymentDto() {
    willReturn(bill).given(billRepository).findOldestUnpaidBillByClientId(CLIENT_ID);
    willReturn(billForPaymentDto).given(billAssembler).assembleBillForPayment(bill);

    BillForPaymentDto dto = billService.getOldestUnpaidBillForClient(CLIENT_ID);

    assertEquals(dto, billForPaymentDto);
  }

}
