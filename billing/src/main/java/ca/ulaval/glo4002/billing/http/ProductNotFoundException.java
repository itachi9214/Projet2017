package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.domain.submision.BillingException;

public class ProductNotFoundException extends BillingException {

  private static final long serialVersionUID = 1L;
  private int productId;

  public ProductNotFoundException(int productId) {
    this.productId = productId;
  }

  public int getProductId() {
    return productId;
  }

}
