package ca.ulaval.glo4002.payment.api.dto;

public class ResponsePaymentDto {

  private long id;
  private String url;

  public ResponsePaymentDto(long id, String url) {
    this.id = id;
    this.url = url;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
