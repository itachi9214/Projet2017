package ca.ulaval.glo4002.billing.api.dto.heartbeat;

public class HeartbeatDto {

  private String token;
  private long date;

  public HeartbeatDto(String token) {
    this.token = token;
    this.date = System.currentTimeMillis();
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

}
