package ca.ulaval.glo4002.billing.domain.submitting;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderedProduct {

  @Id
  @GeneratedValue
  private long id;
  private int productId;
  private float price;
  private String note;
  private int quantity;

  public OrderedProduct(int productId, float price, String note, int quantity) {
    this.productId = productId;
    this.price = price;
    this.note = note;
    this.quantity = quantity;
  }

  public OrderedProduct() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public BigDecimal calculateTotalPrice() {
    return new BigDecimal(price * quantity);
  }

}
