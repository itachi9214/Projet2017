package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;

public class OrderedProduct {

  private int productId;
  private float price;
  private String note;
  private int quantity;

  public OrderedProduct() {
    super();
  }

  public OrderedProduct(int productId, float price, String note, int quantity) {
    this.productId = productId;
    this.price = price;
    this.note = note;
    this.quantity = quantity;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal calculateProduct() {
    return new BigDecimal(price * quantity);
  }

}
