package ca.ulaval.glo4002.billing.infrastructure.submitting;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;
import ca.ulaval.glo4002.billing.domain.submitting.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;
import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingHibernateRepository;
import ca.ulaval.glo4002.billing.infrastructure.submitting.SubmittingNotFoundException;

public class SubmittingHibernateRepositoryTest {

  private static final List<OrderedProduct> ITEMS = Collections.emptyList();
  private static final long CLIENT_NUMBER = 1L;
  private static final DueTerm DUE_TERM = DueTerm.DAYS30;
  private static final Identity BILL_NUMBER = new Identity(1L);

  private SubmissionRepository submissionRepository;
  private EntityManager entityManager;
  private EntityManagerFactory entityManagerFactory;

  @Before
  public void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("billing");
    entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);
    submissionRepository = new SubmittingHibernateRepository(new EntityManagerProvider());
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
  public void givenSubmissionWhenCreateSubmissionThenSubmissionFoundIsTheSame() {
    Submission submission = new Submission(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);

    submissionRepository.createSubmission(submission);

    Submission submissionFound = submissionRepository.findSubmissionById(BILL_NUMBER);
    assertEquals(submission, submissionFound);
  }

  @Test(expected = SubmittingNotFoundException.class)
  public void givenNoSubmissionWhenFindSubmissionThenThrowSubmissionNotFoundException() {
    submissionRepository.findSubmissionById(BILL_NUMBER);
  }

  @Test(expected = SubmittingNotFoundException.class)
  public void givenExistingSubmissionWhenDeleteSubmissionThenThrowSubmissionNotFoundException() {
    Submission submission = new Submission(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    submissionRepository.createSubmission(submission);

    submissionRepository.deleteSubmission(submission);

    submissionRepository.findSubmissionById(BILL_NUMBER);
  }

}
