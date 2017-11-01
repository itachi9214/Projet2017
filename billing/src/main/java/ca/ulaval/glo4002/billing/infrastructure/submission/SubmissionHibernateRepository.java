package ca.ulaval.glo4002.billing.infrastructure.submission;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

public class SubmissionHibernateRepository implements SubmissionRepository {

  private EntityManagerProvider entityManagerProvider;

  public SubmissionHibernateRepository() {
    this.entityManagerProvider = ServiceLocator.getService(EntityManagerProvider.class);
  }

  public SubmissionHibernateRepository(EntityManagerProvider entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public void createSubmission(Submission submission) {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    entityManager.persist(submission);

    transaction.commit();
  }

  @Override
  public Submission findSubmissionById(Identity submissionNumber)
      throws SubmissionNotFoundException {
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    Submission submission = entityManager.find(Submission.class, submissionNumber);

    if (submission == null) {
      throw new SubmissionNotFoundException();
    }
    return submission;
  }

  @Override
  public void deleteSubmission(Submission submission) {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    entityManager.getTransaction().begin();

    entityManager.remove(submission);

    entityManager.getTransaction().commit();
  }

}
