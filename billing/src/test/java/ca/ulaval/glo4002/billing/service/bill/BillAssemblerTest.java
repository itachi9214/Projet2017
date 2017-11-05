package ca.ulaval.glo4002.billing.service.bill;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@RunWith(MockitoJUnitRunner.class)
public class BillAssemblerTest {

  private static final BigDecimal TOTAL_PRICE = new BigDecimal(0);
  private static final BigDecimal PAID_PRICE = new BigDecimal(0);
  private static final List<Object> ITEMS_LIST = Collections.emptyList();
  private static final long CLIENT_NUMBER = 1L;
  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final String URL_FROM_SUBMISSION_NUMBER = "/bills/" + SUBMISSION_NUMBER;

  private BillAssembler billAssembler;
  private LocalDateTime date;
  private DateTimeFormatter formatter;

  @Mock
  private Submission submission;
  @Mock
  private Identity identity;
  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
    willReturn(SUBMISSION_NUMBER).given(identity).getNumber();
    date = LocalDateTime.now();
    billAssembler = new BillAssembler();
  }

  @Test
  public void whenAssembleBillThenDtoShouldBeTheSame() {
    willReturn(identity).given(bill).getBillNumber();
    willReturn(IMMEDIATE).given(bill).getDueTerm();
    willReturn(date).given(bill).getEffectiveDate();
    willReturn(date).given(bill).getExpectedPayment();

    BillDto dto = billAssembler.assembleBill(bill);

    assertTrue(dto.getId().equals(SUBMISSION_NUMBER));
    assertTrue(dto.getDueTerm().equals(IMMEDIATE));
    assertTrue(dto.getEffectiveDate().equals(date.format(formatter).toString()));
    assertTrue(dto.getExpectedPayment().equals(date.format(formatter).toString()));
    assertTrue(dto.getUrl().equals(URL_FROM_SUBMISSION_NUMBER));
  }

  @Test
  public void whenCreateTheBillFromTheSubmissionDataThenBillShouldBeTheSame() {
    willReturn(identity).given(submission).getBillNumber();
    willReturn(IMMEDIATE).given(submission).getDueTerm();
    willReturn(CLIENT_NUMBER).given(submission).getClientId();
    willReturn(ITEMS_LIST).given(submission).getItems();
    willReturn(TOTAL_PRICE).given(submission).getTotalPrice();

    Bill bill = billAssembler.createBillFromSubmission(submission);

    assertTrue(bill.getBillNumber().equals(identity));
    assertTrue(bill.getDueTerm().equals(IMMEDIATE));
    assertTrue(bill.getExpectedPayment().equals(bill.getEffectiveDate()));
    assertTrue(bill.getItems().equals(ITEMS_LIST));
    assertTrue(bill.getTotalPrice().equals(TOTAL_PRICE));
    assertTrue(bill.getClientId().equals(CLIENT_NUMBER));
  }

  @Test
  public void whenAssembleBillForPaymentThenDtoShouldBeTheSame() {
    willReturn(identity).given(bill).getBillNumber();
    willReturn(PAID_PRICE).given(bill).getPaidPrice();

    BillForPaymentDto billForPaymentDto = billAssembler.assembleBillForPayment(bill);

    assertTrue(billForPaymentDto.getBillNumber().equals(identity.getNumber()));
    assertTrue(billForPaymentDto.getPaidPrice().equals(PAID_PRICE));
  }

}
