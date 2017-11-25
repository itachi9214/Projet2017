package ca.ulaval.glo4002.billing.infrastructure.submitting;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

public class SubmittingHibernateRepository implements SubmissionRepository {

  private EntityManagerProvider entityManagerProvider;

  public SubmittingHibernateRepository() {
    this.entityManagerProvider = ServiceLocator.getService(EntityManagerProvider.class);
  }

  public SubmittingHibernateRepository(EntityManagerProvider entityManagerProvider) {
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
      throws SubmittingNotFoundException {
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    Submission submission = entityManager.find(Submission.class, submissionNumber);

    if (submission == null) {
      throw new SubmittingNotFoundException();
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
