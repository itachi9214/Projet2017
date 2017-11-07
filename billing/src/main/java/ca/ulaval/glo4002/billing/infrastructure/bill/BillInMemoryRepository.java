package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;

public class BillInMemoryRepository implements BillRepository {

  private Map<Identity, Bill> bills = new HashMap<>();

  public BillInMemoryRepository() {
  }

  @Override
  public void createBill(Bill bill) throws BillAlreadyExistsException {
    if (billAlreadyExists(bill.getBillNumber())) {
      throw new BillAlreadyExistsException();
    }
    bills.put(bill.getBillNumber(), bill);
  }

  private boolean billAlreadyExists(Identity billId) {
    return bills.containsKey(billId);
  }

  @Override
  public Bill findById(Identity billNumber) throws BillNotFoundException {
    if (!billAlreadyExists(billNumber)) {
      throw new BillNotFoundException();
    }
    return bills.get(billNumber);
  }

  @Override
  public Bill findOldestUnpaidBillByClientId(Long clientId) throws BillNotFoundException {
    Optional<Bill> billFound = bills.values().stream().filter(b -> b.getClientId().equals(clientId))
        .findFirst();

    if (!billFound.isPresent()) {
      throw new BillNotFoundException();
    }
    return billFound.get();
  }

  @Override
  public void updateBill(Bill bill) {
  }

  public void cancelBill(Identity billNumber) {
  }

}
