package ca.ulaval.glo4002.billing.service.submission;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.id.Id;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionAssemblerTest {

  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final BigDecimal SUBMISSION_TOTAL = new BigDecimal(0);
  private static final Long CLIENT_ID = 105L;

  private List<OrderedProduct> items;
  private SubmissionAssembler submissionAssembler;

  @Mock
  private Id id;
  @Mock
  private Submission submission;
  @Mock
  private RequestSubmissionDto requestSubmissionDto;
  @Mock
  private OrderedProduct orderedProduct;

  @Before
  public void setUp() {
    willReturn(SUBMISSION_NUMBER).given(id).getNumber();

    submissionAssembler = new SubmissionAssembler();
  }

  @Test
  public void givenSubmissionAssemblerWhenCreateResponseSubmissionDtoThenShouldBeTheSame() {
    willReturn(id).given(submission).getBillNumber();
    willReturn(IMMEDIATE).given(submission).getDueTerm();
    willReturn(SUBMISSION_TOTAL).given(submission).getTotalPrice();

    ResponseSubmissionDto dto = submissionAssembler.createResponseSubmissionDto(submission);

    assertTrue(dto.getId() == SUBMISSION_NUMBER);
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