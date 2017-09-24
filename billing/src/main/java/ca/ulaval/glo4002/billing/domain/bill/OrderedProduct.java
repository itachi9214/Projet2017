package ca.ulaval.glo4002.billing.domain.bill;

public class OrderedProduct {

  private int productId;
  private float productPrice;
  private String productNote;
  private int orderedProductQuantity;

  public OrderedProduct(int productId, float productPrice, String productNote,
      int orderedProductQuantity) {
    this.productId = productId;
    this.productPrice = productPrice;
    this.productNote = productNote;
    this.orderedProductQuantity = orderedProductQuantity;
  }

  public float calculateProduct() {
    return productPrice * orderedProductQuantity;
  }

}
