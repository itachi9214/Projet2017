package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@Entity
@NamedQueries({
    @NamedQuery(name = "oldestUnpaidBill", query = "SELECT b FROM Bill b WHERE b.clientId =:clientId AND b.billState=:state ORDER BY b.expectedPayment") })
public class Bill extends Submission {

  private LocalDateTime effectiveDate;
  private LocalDateTime expectedPayment;
  @Enumerated
  private BillState billState;
  private BigDecimal paidPrice;

  public Bill() {
    super();
  }

  public Bill(Identity billNumber, DueTerm dueTerm, Long clientId, List<OrderedProduct> items)
      throws NegativeParameterException {
    super(billNumber, dueTerm, clientId, items);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPayment = calculateExpectedPaymentDate();
    this.paidPrice = new BigDecimal(0);
    this.billState = BillState.UNPAID;
  }

  public Bill(DueTerm dueTerm) {
    super(dueTerm);
    this.effectiveDate = LocalDateTime.now();
    this.expectedPayment = calculateExpectedPaymentDate();
    this.paidPrice = new BigDecimal(0);
    this.billState = BillState.UNPAID;
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

  public BigDecimal getPaidPrice() {
    return paidPrice;
  }

  public void setPaidPrice(BigDecimal paidPrice) {
    this.paidPrice = paidPrice;
  }

  public void setEffectiveDate(LocalDateTime effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public LocalDateTime calculateExpectedPaymentDate() {
    this.expectedPayment = effectiveDate.plusMonths(dueTerm.getMonthsQuantity());
    return expectedPayment;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Bill) {
      return ((Bill) obj).getBillNumber().equals(billNumber);
    }
    return false;
  }

}
