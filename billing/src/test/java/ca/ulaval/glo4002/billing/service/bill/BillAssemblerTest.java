package ca.ulaval.glo4002.billing.service.bill;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.domain.DueTerm;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.id.Id;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.service.bill.BillAssembler;

@RunWith(MockitoJUnitRunner.class)
public class BillAssemblerTest {

  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final String URL_FROM_SUBMISSION_NUMBER = "/bills/" + SUBMISSION_NUMBER;

  private BillAssembler billAssembler;
  private LocalDateTime date;
  private DateTimeFormatter formatter;

  @Mock
  Submission submission;
  @Mock
  private Id id;
  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:MM:ss.SSS'Z'");
    given(id.getNumber()).willReturn(SUBMISSION_NUMBER);
    date = LocalDateTime.now();
    billAssembler = new BillAssembler();
  }

  @Test
  public void whenAssembleBillThenDtoShouldBeTheSame() {
    given(bill.getBillNumber()).willReturn(id);
    given(bill.getDueTerm()).willReturn(IMMEDIATE);
    given(bill.getEffectiveDate()).willReturn(date);
    given(bill.getExpectedPaiement()).willReturn(date);

    BillDto dto = billAssembler.assembleBill(bill);

    assertTrue(dto.getId().equals(SUBMISSION_NUMBER));
    assertTrue(dto.getDueTerm().equals(IMMEDIATE));
    assertTrue(dto.getEffectiveDate().equals(date.format(formatter).toString()));
    assertTrue(dto.getExpectedPayment().equals(date.format(formatter).toString()));
    assertTrue(dto.getUrl().equals(URL_FROM_SUBMISSION_NUMBER));
  }

}
