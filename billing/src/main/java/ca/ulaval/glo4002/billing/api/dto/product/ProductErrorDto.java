package ca.ulaval.glo4002.billing.api.dto.product;

public class ProductErrorDto {

  private String error;
  private String description;
  private String entity;

  public ProductErrorDto(String error, String description, String entity) {
    super();
    this.error = error;
    this.description = description;
    this.entity = entity;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

}
