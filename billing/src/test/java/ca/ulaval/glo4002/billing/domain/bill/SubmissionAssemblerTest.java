package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionAssemblerTest {
  private static final Long SOUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIAT = DueTerm.IMMEDIATE;
  private static final BigDecimal SOUBMISSION_TOTAL = new BigDecimal(100);

  @Mock
  Submission submission;
  @Mock
  ResponseSubmissionDto responseSubmissionDto;

  public SubmissionAssembler submissionAssembler;

  @Before
  public void setUp() {
    submissionAssembler = new SubmissionAssembler();
  }

  @Test
  public void givenSubmissionAssemblerWhenCreateSubmissionThenReturnResponseSubmissionDto() {
    given(submission.getBillNumber()).willReturn(SOUBMISSION_NUMBER);
    given(submission.getDueTerm()).willReturn(IMMEDIAT);
    given(submission.getSubmissionTotal()).willReturn(SOUBMISSION_TOTAL);

    ResponseSubmissionDto dto = submissionAssembler.create(submission);

    assertTrue(dto.getBillNumber() == SOUBMISSION_NUMBER);
    assertTrue(dto.getDueTerm() == IMMEDIAT);
    assertTrue(dto.getTotal() == SOUBMISSION_TOTAL);

  }

}