package ca.ulaval.glo4002.billing.infrastructure.submission;

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
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

public class SubmissionHibernateRepositoryTest {

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
    submissionRepository = new SubmissionHibernateRepository(new EntityManagerProvider());
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

  @Test(expected = SubmissionNotFoundException.class)
  public void givenNoSubmissionWhenFindSubmissionThenThrowException() {
    submissionRepository.findSubmissionById(BILL_NUMBER);
  }

  @Test(expected = SubmissionNotFoundException.class)
  public void givenExistingSubmissionWhenDeleteSubmissionThenSubmissionCannotBeFound() {
    Submission submission = new Submission(BILL_NUMBER, DUE_TERM, CLIENT_NUMBER, ITEMS);
    submissionRepository.createSubmission(submission);

    submissionRepository.deleteSubmission(submission);

    submissionRepository.findSubmissionById(BILL_NUMBER);
  }

}
