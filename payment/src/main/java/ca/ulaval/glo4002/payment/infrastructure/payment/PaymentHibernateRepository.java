package ca.ulaval.glo4002.payment.infrastructure.payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.domain.identity.Identity;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentRepository;
import ca.ulaval.glo4002.payment.infrastructure.EntityManagerProvider;

public class PaymentHibernateRepository implements PaymentRepository {

  private EntityManagerProvider entityManagerProvider;

  public PaymentHibernateRepository() {
    this.entityManagerProvider = ServiceLocator.getService(EntityManagerProvider.class);
  }

  public PaymentHibernateRepository(EntityManagerProvider entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public void savePayment(Payment payment) {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    entityManager.persist(payment);

    transaction.commit();
  }

  @Override
  public Payment findPaymentById(Identity id) throws PaymentNotFoundException {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    Payment payment = entityManager.find(Payment.class, id);

    if (payment == null) {
      throw new PaymentNotFoundException();
    }
    return payment;
  }

}
