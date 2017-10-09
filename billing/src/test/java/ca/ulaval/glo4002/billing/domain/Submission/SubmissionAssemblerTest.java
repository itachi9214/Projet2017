package ca.ulaval.glo4002.billing.domain.Submission;

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
import ca.ulaval.glo4002.billing.domain.Submission.Id;
import ca.ulaval.glo4002.billing.domain.Submission.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionAssemblerTest {

  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final BigDecimal SUBMISSION_TOTAL = new BigDecimal(0);
  private static final Long CLIENT_ID = 105L;

  @Mock
  private Id id;
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
    given(id.getNumber()).willReturn(SUBMISSION_NUMBER);

    submissionAssembler = new SubmissionAssembler();
  }

  @Test
  public void givenSubmissionAssemblerWhenCreateResponseSubmissionDtoThenShouldBeTheSame() {
    given(submission.getBillNumber()).willReturn(id);
    given(submission.getDueTerm()).willReturn(IMMEDIATE);
    given(submission.getTotalPrice()).willReturn(SUBMISSION_TOTAL);

    ResponseSubmissionDto dto = submissionAssembler.createResponseSubmissionDto(submission);

    assertTrue(dto.getBillNumber() == SUBMISSION_NUMBER);
    assertTrue(dto.getDueTerm() == IMMEDIATE);
    assertTrue(dto.getTotal() == SUBMISSION_TOTAL);
  }

  @Test
  public void givenSubmissionAssemblerWhenCreateSubmissionThenShouldBeTheSame()
      throws NegativeParameterException {
    requestSubmissionDto.setClientId(CLIENT_ID);
    requestSubmissionDto.setDueTerm(IMMEDIATE);
    requestSubmissionDto.setItems(items);

    Submission submissionTest = submissionAssembler.createSubmission(requestSubmissionDto);

    assertTrue(submissionTest instanceof Submission);
  }

}