package ca.ulaval.glo4002.billing.domain.Submission;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;

@RunWith(MockitoJUnitRunner.class)
public class BillAssemblerTest {

  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final String URL_FROM_SUBMISSION_NUMBER = "/bills/" + SUBMISSION_NUMBER;
  private BillAssembler billAssembler;
  private LocalDate date;

  @Mock
  private Id id;
  @Mock
  private Bill bill;

  @Before
  public void setUp() {
    given(id.getNumber()).willReturn(SUBMISSION_NUMBER);
    date = LocalDate.now();
    billAssembler = new BillAssembler();
  }

  @Test
  public void whenAssembleBillThenDtoShouldBeTheSame() {
    given(bill.getBillNumber()).willReturn(id);
    given(bill.getDueTerm()).willReturn(IMMEDIATE);
    given(bill.getEffectiveDate()).willReturn(date);
    given(bill.calculateExpectedPaiementDate()).willReturn(date);

    BillDto dto = billAssembler.assembleBill(bill, id);

    assertTrue(dto.getId().equals(SUBMISSION_NUMBER));
    assertTrue(dto.getDueTerm().equals(IMMEDIATE));
    assertTrue(dto.getEffectiveDate().equals(date));
    assertTrue(dto.getExpectedPayment().equals(date));
    assertTrue(dto.getUrl().equals(URL_FROM_SUBMISSION_NUMBER));
  }

}
