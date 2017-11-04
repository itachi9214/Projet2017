package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.BillState;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionNotFoundException;

@NamedQuery(name = "listOfBillsOrderedByOldest", query = "SELECT b FROM Bill b WHERE b.clientId =:clientId AND b.billState=:state ORDER BY b.expectedPayment")
public class BillHibernateRepository implements BillRepository {

  private static final String STATE = "state";
  private static final String CLIENT_ID = "clientId";

  private EntityManagerProvider entityManagerProvider;
  private SubmissionRepository submissionRepository;

  public BillHibernateRepository() {
    this.entityManagerProvider = ServiceLocator.getService(EntityManagerProvider.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
  }

  public BillHibernateRepository(SubmissionRepository submissionRepository,
      EntityManagerProvider entityManagerProvider) {
    this.submissionRepository = submissionRepository;
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public void createBill(Bill bill) throws BillAlreadyExistsException {
    if (billAlreadyExists(bill.getBillNumber())) {
      throw new BillAlreadyExistsException();
    }

    deleteSubmissionToClearPlaceForBill(bill);

    EntityManager entityManager = entityManagerProvider.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    entityManager.persist(bill);

    transaction.commit();
  }

  private void deleteSubmissionToClearPlaceForBill(Bill bill) {
    try {
      Submission submissionNotYetAccepted = submissionRepository
          .findSubmissionById(bill.getBillNumber());
      submissionRepository.deleteSubmission(submissionNotYetAccepted);
    } catch (SubmissionNotFoundException exception) {
    }
  }

  private boolean billAlreadyExists(Identity billId) {
    boolean exists;
    try {
      findById(billId);
      exists = true;
    } catch (BillNotFoundException exception) {
      exists = false;
    }
    return exists;
  }

  @Override
  public Bill findById(Identity billNumber) throws BillNotFoundException {
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    Bill bill = entityManager.find(Bill.class, billNumber);

    if (bill == null) {
      throw new BillNotFoundException();
    }
    return bill;
  }

  @Override

  public List<Bill> findAllByClientId(Long clientId) throws BillNotFoundException {
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    TypedQuery<Bill> query = entityManager.createNamedQuery("listOfBillsOrderedByOldest",
        Bill.class);

    query.setParameter(CLIENT_ID, clientId);
    query.setParameter(STATE, BillState.UNPAID);
    List<Bill> bills = query.getResultList();

    if (bills.isEmpty()) {
      throw new BillNotFoundException();
    }
    return bills;
  }

}
