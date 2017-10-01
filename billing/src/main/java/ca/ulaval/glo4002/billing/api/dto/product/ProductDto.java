package ca.ulaval.glo4002.billing.api.dto.product;

import java.math.BigDecimal;

public class ProductDto {

  private Integer productId;
  private String productName;
  private BigDecimal productUnitPrice;

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getProductUnitPrice() {
    return productUnitPrice;
  }

  public void setProductUnitPrice(BigDecimal productUnitPrice) {
    this.productUnitPrice = productUnitPrice;
  }

}
