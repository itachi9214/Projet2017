package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionAssemblerTest {
  private static final Long SOUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIAT = DueTerm.IMMEDIATE;
  private static final BigDecimal SOUBMISSION_TOTAL = new BigDecimal(0);
  private static final Long CLIENT_ID = 105L;

  @Mock
  private Submission submission;
  @Mock
  private RequestSubmissionDto requestSubmissionDto;
  @Mock
  private OrderedProduct orderedProduct;
  private List<OrderedProduct> items;

  private SubmissionAssembler submissionAssembler;

  @Before
  public void setUp() {
    submissionAssembler = new SubmissionAssembler();
  }

  @Test
  public void givenSubmissionAssemblerWhenCreateResponseSubmissionDtoThenShouldBeTheSame() {
    given(submission.getBillNumber()).willReturn(SOUBMISSION_NUMBER);
    given(submission.getDueTerm()).willReturn(IMMEDIAT);
    given(submission.getTotalPrice()).willReturn(SOUBMISSION_TOTAL);

    ResponseSubmissionDto dto = submissionAssembler.createResponseSubmissionDto(submission);

    assertTrue(dto.getBillNumber() == SOUBMISSION_NUMBER);
    assertTrue(dto.getDueTerm() == IMMEDIAT);
    assertTrue(dto.getTotal() == SOUBMISSION_TOTAL);
  }

  @Test
  public void givenSubmissiAssemblerWhenCreatSubmissionThenShouldBeTheSame() {
    requestSubmissionDto.setClientId(CLIENT_ID);
    requestSubmissionDto.setDueTerm(IMMEDIAT);
    requestSubmissionDto.setItems(items);

    Submission submissionTest = submissionAssembler.createSubmission(requestSubmissionDto);

    assertTrue(submissionTest instanceof Submission);
  }

}