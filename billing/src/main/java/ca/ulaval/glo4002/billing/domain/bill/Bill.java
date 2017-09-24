package ca.ulaval.glo4002.billing.domain.bill;

import java.util.List;

public class Bill {
  int billNumber;
  DueTerm dueTerm;
  int clientNumber;
  List<OrderedProduct> products;
  float somme;

  public float Billcalculate() {
    for (OrderedProduct product : products) {
      float somme = product.calculateProduct();
    }
    return somme;
  }

}
