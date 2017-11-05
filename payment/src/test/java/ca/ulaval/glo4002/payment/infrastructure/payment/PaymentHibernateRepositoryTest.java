package ca.ulaval.glo4002.payment.infrastructure.payment;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.infrastructure.EntityManagerProvider;

public class PaymentHibernateRepositoryTest {

  private static final int AMOUNT = 3;
  private static final long PAYMENT_ID = 1L;

  private PaymentHibernateRepository paymentRepository;
  private EntityManager entityManager;
  private EntityManagerFactory entityManagerFactory;

  @Before
  public void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("payment");
    entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);
    paymentRepository = new PaymentHibernateRepository(new EntityManagerProvider());
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

  @Ignore
  @Test
  public void whenSavePaymentThenPaymentFoundIsTheSame() {
    Payment payment = new Payment(PAYMENT_ID, AMOUNT);

    paymentRepository.savePayment(payment);

    assertEquals(payment, paymentRepository.findPaymentById(PAYMENT_ID));
  }

  @Ignore
  @Test(expected = PaymentNotFoundException.class)
  public void givenNotExistingPaymentNumberWhenFindPaymentThenThrowException() {
    paymentRepository.findPaymentById(PAYMENT_ID);
  }

}
