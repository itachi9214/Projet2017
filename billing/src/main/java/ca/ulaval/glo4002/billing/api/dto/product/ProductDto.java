package ca.ulaval.glo4002.billing.api.dto.product;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

  private Integer id;
  private String name;
  private BigDecimal unitPrice;

  public ProductDto() {
  }

  public ProductDto(Integer id, String name, BigDecimal unitPrice) {
    this.id = id;
    this.name = name;
    this.unitPrice = unitPrice;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

}
