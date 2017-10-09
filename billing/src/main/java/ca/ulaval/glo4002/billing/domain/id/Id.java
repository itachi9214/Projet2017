package ca.ulaval.glo4002.billing.domain.id;

import java.util.UUID;

public class Id {

  private Long number;

  public Id() {
    generateAndSetUniqueNumber();
  }

  public Id(Long number) {
    this.number = number;
  }

  public Long getNumber() {
    return number;
  }

  private void generateAndSetUniqueNumber() {
    this.number = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
  }

  @Override
  public String toString() {
    return number.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Id) {
      return ((Id) obj).getNumber().equals(this.number);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.number);
  }

}
