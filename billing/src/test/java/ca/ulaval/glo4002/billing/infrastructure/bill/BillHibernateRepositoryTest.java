package ca.ulaval.glo4002.billing.infrastructure.bill;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.BillState;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;
import ca.ulaval.glo4002.billing.domain.submitting.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class BillHibernateRepositoryTest {

  private static final List<OrderedProduct> ITEMS = Collections.emptyList();
  private static final long CLIENT_NUMBER = 1L;
  private static final DueTerm DUE_TERM = DueTerm.DAYS30;
  private static final Identity BILL_NUMBER = new Identity(1L);
  private static final Identity OTHER_BILL_NUMBER = new Identity(2L);
  private static final DueTerm OLDER_DUE_TERM = DueTerm.IMMEDIATE;

  private BillRepository billRepository;
  private EntityManager entityManager;
  private EntityManagerFactory entityManagerFactory;

  @Mock
  private SubmissionRepository submissionRepository;
  @Mock
  private Submission submission;

  @Before
  public void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("billing");
    entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);
    billRepository = new BillHibernateRepository(submissionRepository, new EntityManagerProvider());
  }

  @After
  public void tearDown() {
    if (entityManager != null) {
      entityManager.close();
      entityManager = null;
    }
    EntityManagerProvider.clearEntityManager();
    entityManagerFactory.close();
  }

  @Test
  public void givenBillWhenCreateBillThenBillIsFound() {
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);

    billRepository.createBill(bill);

    assertEquals(bill, billRepository.findById(BILL_NUMBER));
  }

  @Test
  public void givenSubmissionAndBillWhenCreateBillThenVerifySubmissionIsDeleted() {
    willReturn(submission).given(submissionRepository).findSubmissionById(BILL_NUMBER);
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);

    billRepository.createBill(bill);

    verify(submissionRepository).deleteSubmission(submission);
  }

  @Test(expected = BillAlreadyExistsException.class)
  public void givenExistingBillWhenCreateBillThenThrowBillAlreadyExistsException() {
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    billRepository.createBill(bill);

    billRepository.createBill(bill);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenNoBillWhenFindByIdThenThrowBillNotFoundException() {
    billRepository.findById(BILL_NUMBER);
  }

  @Test
  public void givenClientIdWhenFindOldestUnpaidBillByClientIdThenClientIdIsTheSame() {
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    billRepository.createBill(bill);

    Bill billFound = billRepository.findOldestUnpaidBillByClientId(CLIENT_NUMBER);

    assertEquals(CLIENT_NUMBER, billFound.getClientId().longValue());
  }

  @Test
  public void givenTwoUnpaidBillsWithSameClientIdWhenFindOldestUnpaidBillByClientIdThenOldestIsReturned() {
    Bill olderBill = new Bill(OTHER_BILL_NUMBER, OLDER_DUE_TERM, CLIENT_NUMBER, ITEMS);
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    billRepository.createBill(olderBill);
    billRepository.createBill(bill);

    Bill billFound = billRepository.findOldestUnpaidBillByClientId(CLIENT_NUMBER);

    assertEquals(olderBill, billFound);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenNoBillWhenFindOldestUnpaidBillByClientIdThenThrowBillNotFoundException() {
    billRepository.findOldestUnpaidBillByClientId(CLIENT_NUMBER);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenPaidBillWhenFindOldestUnpaidBillByClientIdThenThrowBillNotFoundException() {
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    billRepository.createBill(bill);
    bill.setBillState(BillState.PAID);
    billRepository.updateBill(bill);

    billRepository.findOldestUnpaidBillByClientId(CLIENT_NUMBER);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenUnexistingBillWhenCancelBillThenThrowBillNotFoundException() {
    billRepository.cancelBill(BILL_NUMBER);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenCanceledBillWhenFindBillThenThrowBillNotFoundException() {
    Bill bill = new Bill(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    billRepository.createBill(bill);
    billRepository.cancelBill(BILL_NUMBER);

    billRepository.findById(BILL_NUMBER);
  }

}
