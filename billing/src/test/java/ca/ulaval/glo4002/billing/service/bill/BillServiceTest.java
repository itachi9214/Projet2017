package ca.ulaval.glo4002.billing.service.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  private static final long BILL_NUMBER = 10L;
  private static final long CLIENT_ID = 100L;
  private static final BigDecimal PAID_PRICE = new BigDecimal(10);

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
    billForPaymentDto = new BillForPaymentDto(BILL_NUMBER, PAID_PRICE);
    willReturn(identity).given(identityFactory).createIdFromNumber(BILL_NUMBER);
    willReturn(bill).given(billRepository).findById(identity);
    willReturn(billForPaymentDto).given(billAssembler).assembleBillForPayment(bill);
    willReturn(bill).given(billRepository).findOldestUnpaidBillByClientId(CLIENT_ID);
    willReturn(billForPaymentDto).given(billAssembler).assembleBillForPayment(bill);
    willReturn(submission).given(submissionRepository).findSubmissionById(identity);
    willReturn(bill).given(billAssembler).createBillFromSubmission(submission);
  }

  @Test
  public void givenBillNumberWhenCreateBillThenVerifySubmissionIsFoundByIdentity() {
    billService.createBill(BILL_NUMBER);

    verify(submissionRepository).findSubmissionById(identity);
  }

  @Test
  public void givenBillNumberWhenCreateBillThenVerifyBillIsCreated() {
    billService.createBill(BILL_NUMBER);

    verify(billRepository).createBill(bill);
  }

  @Test
  public void givenCliendIdWhenGetOldestUnpaidBillForClientThenVerifyOldestUnpaidBillIsFoundByClientId() {
    billService.getOldestUnpaidBillForClient(CLIENT_ID);

    verify(billRepository).findOldestUnpaidBillByClientId(CLIENT_ID);
  }

  @Test
  public void givenClientIdWhenGetOldestUnpaidBillForClientThenBillForPaymentDtoIsCorrect() {
    BillForPaymentDto dto = billService.getOldestUnpaidBillForClient(CLIENT_ID);

    assertEquals(billForPaymentDto, dto);
  }

  @Test
  public void givenBillForPaymentDtoWhenUpdateBillAfterPaymentThenVerifyBillIsUpdated() {
    billService.updateBillAfterPayment(billForPaymentDto);

    verify(bill).updateAfterPayment(PAID_PRICE);
  }

  @Test
  public void givenBillForPaymentDtoWhenUpdateBillAfterPaymentThenVerifyBillUpdateIsSaved() {
    billService.updateBillAfterPayment(billForPaymentDto);

    verify(billRepository).updateBill(bill);
  }

}
