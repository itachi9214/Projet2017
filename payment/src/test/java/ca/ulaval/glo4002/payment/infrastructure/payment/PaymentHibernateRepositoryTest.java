package ca.ulaval.glo4002.payment.infrastructure.payment;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.payment.domain.identity.Identity;
import ca.ulaval.glo4002.payment.domain.payment.Payment;
import ca.ulaval.glo4002.payment.domain.payment.PaymentMethod;
import ca.ulaval.glo4002.payment.domain.payment.PaymentSource;
import ca.ulaval.glo4002.payment.infrastructure.EntityManagerProvider;

public class PaymentHibernateRepositoryTest {

  private static final String ACCOUNT = "account";
  private static final Identity PAYMENT_NUMBER = new Identity(1L);
  private static final long CLIENT_ID = 3L;
  private static final float AMOUNT = 30;

  private PaymentHibernateRepository paymentRepository;
  private EntityManager entityManager;
  private EntityManagerFactory entityManagerFactory;
  private PaymentMethod paymentMethod;

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

  @Test
  public void givenPaymentWhenSavePaymentThenPaymentFoundIsTheSame() {
    paymentMethod = new PaymentMethod(ACCOUNT, PaymentSource.CHECK);
    Payment payment = new Payment(PAYMENT_NUMBER, AMOUNT, CLIENT_ID, paymentMethod);

    paymentRepository.savePayment(payment);

    Payment paymentFound = paymentRepository.findPaymentById(PAYMENT_NUMBER);
    assertEquals(payment, paymentFound);
  }

  @Test(expected = PaymentNotFoundException.class)
  public void givenNoPaymentWhenFindPaymentThenThrowPaymentNotFoundException() {
    paymentRepository.findPaymentById(PAYMENT_NUMBER);
  }

}
