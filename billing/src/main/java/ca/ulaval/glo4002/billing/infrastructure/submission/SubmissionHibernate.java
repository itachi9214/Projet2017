package ca.ulaval.glo4002.billing.infrastructure.submission;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

public class SubmissionHibernate implements SubmissionRepository {

  private EntityManagerProvider entityManagerProvider;

  public SubmissionHibernate() {
    this.entityManagerProvider = new EntityManagerProvider();
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
  public Submission findSubmissionById(Identity submissionNumber) {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    return entityManager.find(Submission.class, submissionNumber);
  }

}
