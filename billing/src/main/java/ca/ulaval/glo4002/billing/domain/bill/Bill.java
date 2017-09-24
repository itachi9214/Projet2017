package ca.ulaval.glo4002.billing.domain.bill;

import java.util.List;

import ca.ulaval.glo4002.billing.domain.DueTerm;

public class Bill {

  int billNumber;
  DueTerm dueTerm;
  int clientNumber;
  List<OrderedProduct> products;
  float billTotal;

  public float calculateBill() {
    for (OrderedProduct product : products) {
      billTotal += product.calculateProduct();
    }
    return billTotal;
  }

}
