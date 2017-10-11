package ca.ulaval.glo4002.billing.infrastructure.bill;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;

public class BillHibernate implements BillRepository {

  private EntityManagerProvider entityManagerProvider;

  public BillHibernate() {
    this.entityManagerProvider = new EntityManagerProvider();
  }

  @Override
  public void createBill(Bill bill) {
    EntityManager entityManager = entityManagerProvider.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    entityManager.persist(bill);

    transaction.commit();
  }

}
