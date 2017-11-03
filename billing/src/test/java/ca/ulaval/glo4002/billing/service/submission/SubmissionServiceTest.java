package ca.ulaval.glo4002.billing.service.submission;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.submision.ClientRepository;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.ProductRepository;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionServiceTest {

  private static final DueTerm MONTH_AWAY_DUE_TERM = DueTerm.DAYS30;
  private static final DueTerm IMMEDIATE_DUE_TERM = DueTerm.IMMEDIATE;
  private static final int NEGATIVE_ITEM_QUANTITY = -6;
  private static final Long CLIENT_ID = 1L;

  private RequestSubmissionDto requestSubmissionDto;
  private SubmissionService submissionService;
  private ClientDto clientDto;

  @Mock
  private Submission submission;
  @Mock
  private SubmissionRepository submissionRepository;
  @Mock
  private SubmissionAssembler submissionAssembler;
  @Mock
  private ClientRepository clientRepository;
  @Mock
  private ProductRepository productRepository;
  @Mock
  private OrderedProduct item;

  @Before
  public void setUp() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(item);
    requestSubmissionDto = new RequestSubmissionDto(CLIENT_ID, new Date(), DueTerm.DAYS30, items);

    submissionService = new SubmissionService(submissionAssembler, submissionRepository,
        clientRepository, productRepository);
    clientDto = new ClientDto();

    willReturn(submission).given(submissionAssembler).createSubmission(requestSubmissionDto);
    willReturn(clientDto).given(clientRepository).getClientDto(anyLong());
  }

  @Test
  public void whenCreateSubmissionThenVerifyCreateSubmissionBySubmissionRepository()
      throws NegativeParameterException {
    submissionService.createSubmission(requestSubmissionDto);

    verify(submissionRepository).createSubmission(submission);
  }

  @Test
  public void whenCreateSubmissionThenVerifyCreateSubmissionBySubmissionAssembler()
      throws NegativeParameterException {
    submissionService.createSubmission(requestSubmissionDto);

    verify(submissionAssembler).createSubmission(requestSubmissionDto);
  }

  @Test
  public void whenGetAndVerifyClientExistsThenVerifyClientIsFound() {
    submissionService.getAndVerifyClientExists(CLIENT_ID);

    verify(clientRepository).getClientDto(CLIENT_ID);
  }

  @Test
  public void givenListOfItemsWhenVerifyProductsExistThenVerifyProductIsFound() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(item);

    submissionService.verifyProductsExist(items);

    verify(productRepository).getProductDto(anyInt());
  }

  @Test(expected = NegativeParameterException.class)
  public void givenProductWithNegativeQuantityWhenCreateSubmissionThenThrowException() {
    willReturn(NEGATIVE_ITEM_QUANTITY).given(item).getQuantity();

    submissionService.createSubmission(requestSubmissionDto);
  }

  @Test
  public void givenRequestWithoutDueTermWhenSetToDefaultIfNeededThenIsSetCorrectly() {
    requestSubmissionDto.setDueTerm(null);

    submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto, IMMEDIATE_DUE_TERM);

    assertEquals(requestSubmissionDto.getDueTerm(), IMMEDIATE_DUE_TERM);
  }

  @Test
  public void givenRequestWithDueTermWhenSetToDefaultIfNeededThenIsNotModified() {
    requestSubmissionDto.setDueTerm(MONTH_AWAY_DUE_TERM);

    submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto, IMMEDIATE_DUE_TERM);

    assertEquals(requestSubmissionDto.getDueTerm(), MONTH_AWAY_DUE_TERM);
  }

}
