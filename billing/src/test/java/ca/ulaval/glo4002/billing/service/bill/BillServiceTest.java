package ca.ulaval.glo4002.billing.service.bill;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.DueTerm;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.id.Id;
import ca.ulaval.glo4002.billing.domain.id.IdFactory;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.service.bill.BillService;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  private static final long BILL_NUMBER = 10l;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  @Mock
  Submission submission;
  @Mock
  BillRepository billRepository;
  @Mock
  Bill bill;
  @Mock
  IdFactory idFactory;
  @Mock
  BillAssembler billAssembler;
  @Mock
  SubmissionRepository submissionRepository;

  private BillService billService;
  private Id id;

  @Before
  public void setUp() {
    billService = new BillService(billRepository, billAssembler, submissionRepository);
    id = new Id(BILL_NUMBER);
  }

  @Test
  public void WhenCreateBillthenVerifyTheAllMethodsIsCall() {
    given(submissionRepository.findSubmissionById(id)).willReturn(submission);
    given(idFactory.createIdFromNumber(BILL_NUMBER)).willReturn(id);
    given(submission.getDueTerm()).willReturn(IMMEDIATE);
    given(billAssembler.createTheBillFromTheSubmissionData(submission)).willReturn(bill);

    billService.createBill(BILL_NUMBER);

    verify(submissionRepository).findSubmissionById(idFactory.createIdFromNumber(BILL_NUMBER));
    verify(billAssembler).createTheBillFromTheSubmissionData(submission);
    verify(billRepository).createBill(bill);
    verify(billAssembler).assembleBill(bill);
  }
}
