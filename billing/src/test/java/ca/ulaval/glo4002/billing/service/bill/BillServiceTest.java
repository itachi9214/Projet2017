package ca.ulaval.glo4002.billing.service.bill;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  private static final long BILL_NUMBER = 10L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;

  private BillService billService;
  private Identity identity;

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
  }

  @Test
  public void whenCreateBillthenVerifyTheAllMethodsIsCall() {
    willReturn(submission).given(submissionRepository).findSubmissionById(identity);
    willReturn(identity).given(identityFactory).createIdFromNumber(BILL_NUMBER);
    willReturn(IMMEDIATE).given(submission).getDueTerm();
    willReturn(bill).given(billAssembler).createTheBillFromTheSubmissionData(submission);

    billService.createBill(BILL_NUMBER);

    verify(submissionRepository).findSubmissionById(identity);
    verify(billAssembler).createTheBillFromTheSubmissionData(submission);
    verify(billRepository).createBill(bill);
    verify(billAssembler).assembleBill(bill);
  }

}
