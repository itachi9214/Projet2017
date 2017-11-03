package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@Entity
public class Bill extends Submission {

  private LocalDateTime effectiveDate;
  private LocalDateTime expectedPayment;
  @Enumerated
  private BillState billState;

  public Bill() {
    super();
  }

  public Bill(Identity billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPayment = calculateExpectedPaymentDate();
    this.billState = BillState.UNPAID;
  }

  public Bill(Identity billNumber, BigDecimal totalPrice, Long clientId, BillState billState) {
    this.billNumber = billNumber;
    this.totalPrice = totalPrice;
    this.clientId = clientId;
    this.billState = billState;
  }

  public Bill(DueTerm dueTerm) {
    super(dueTerm);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPayment = calculateExpectedPaymentDate();
  }

  public LocalDateTime getEffectiveDate() {
    return effectiveDate;
  }

  public LocalDateTime getExpectedPayment() {
    return expectedPayment;
  }

  public void setExpectedPayment(LocalDateTime expectedPayment) {
    this.expectedPayment = expectedPayment;
  }

  public BillState getBillState() {
    return billState;
  }

  public void setBillState(BillState billState) {
    this.billState = billState;
  }

  public LocalDateTime calculateExpectedPaymentDate() {
    this.expectedPayment = effectiveDate.plusMonths(dueTerm.getMonthsQuantity());
    return expectedPayment;
  }

}
