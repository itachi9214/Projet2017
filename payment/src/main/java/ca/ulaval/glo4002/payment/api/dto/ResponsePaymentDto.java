package ca.ulaval.glo4002.payment.api.dto;

public class ResponsePaymentDto {

  private Long id;
  private String url;

  public ResponsePaymentDto(Long id, String url) {
    this.id = id;
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
