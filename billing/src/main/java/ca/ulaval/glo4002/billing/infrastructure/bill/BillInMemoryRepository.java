package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
  public List<Bill> findAllByClientId(Long clientId) throws BillNotFoundException {
    List<Bill> billsFound = bills.values().stream().filter(b -> b.getClientId().equals(clientId))
        .collect(Collectors.toList());

    if (billsFound.isEmpty()) {
      throw new BillNotFoundException();
    }
    return billsFound;
  }

}
