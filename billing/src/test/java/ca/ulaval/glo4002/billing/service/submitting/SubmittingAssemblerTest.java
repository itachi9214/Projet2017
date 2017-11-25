package ca.ulaval.glo4002.billing.service.submitting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submitting.RequestSubmittingDto;
import ca.ulaval.glo4002.billing.api.dto.submitting.ResponseSubmittingDto;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;
import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submitting.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.service.submitting.SubmittingAssembler;

@RunWith(MockitoJUnitRunner.class)
public class SubmittingAssemblerTest {

  private static final Long SUBMISSION_NUMBER = 100L;
  private static final DueTerm IMMEDIATE = DueTerm.IMMEDIATE;
  private static final BigDecimal SUBMISSION_TOTAL = new BigDecimal(0);
  private static final Long CLIENT_ID = 105L;

  private List<OrderedProduct> items;
  private SubmittingAssembler submissionAssembler;

  @Mock
  private Identity identity;
  @Mock
  private Submission submission;
  @Mock
  private RequestSubmittingDto requestSubmissionDto;
  @Mock
  private OrderedProduct orderedProduct;
  @Mock
  private IdentityFactory identityFactory;

  @Before
  public void setUp() {
    willReturn(SUBMISSION_NUMBER).given(identity).getNumber();

    submissionAssembler = new SubmittingAssembler(identityFactory);
  }

  @Test
  public void whenCreateResponseSubmissionDtoThenDtoShouldBeTheSame() {
    willReturn(identity).given(submission).getBillNumber();
    willReturn(IMMEDIATE).given(submission).getDueTerm();
    willReturn(SUBMISSION_TOTAL).given(submission).getTotalPrice();

    ResponseSubmittingDto dto = submissionAssembler.createResponseSubmissionDto(submission);

    assertEquals(SUBMISSION_NUMBER, dto.getId());
    assertEquals(IMMEDIATE, dto.getDueTerm());
    assertEquals(SUBMISSION_TOTAL, dto.getTotal());
  }

  @Test
  public void whenCreateSubmissionThenSubmissionShouldBeTheSame()
      throws NegativeParameterException {
    requestSubmissionDto.setClientId(CLIENT_ID);
    requestSubmissionDto.setDueTerm(IMMEDIATE);
    requestSubmissionDto.setItems(items);

    Submission submissionTest = submissionAssembler.createSubmission(requestSubmissionDto);

    assertTrue(submissionTest instanceof Submission);
  }

}
