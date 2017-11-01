package ca.ulaval.glo4002.billing.domain.identity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class Identity implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long number;

  public Identity() {
    generateAndSetUniqueNumber();
  }

  public Identity(Long number) {
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
    if (obj instanceof Identity) {
      return ((Identity) obj).getNumber().equals(this.number);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.number);
  }

}
