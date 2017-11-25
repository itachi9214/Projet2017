package ca.ulaval.glo4002.billing.service.submitting;

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
import ca.ulaval.glo4002.billing.api.dto.submitting.RequestSubmittingDto;
import ca.ulaval.glo4002.billing.domain.submitting.ClientRepository;
import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;
import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submitting.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submitting.ProductRepository;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.service.submitting.SubmittingAssembler;
import ca.ulaval.glo4002.billing.service.submitting.SubmittingService;

@RunWith(MockitoJUnitRunner.class)
public class SubmittingServiceTest {

  private static final DueTerm MONTH_AWAY_DUE_TERM = DueTerm.DAYS30;
  private static final DueTerm IMMEDIATE_DUE_TERM = DueTerm.IMMEDIATE;
  private static final int NEGATIVE_ITEM_QUANTITY = -6;
  private static final Long CLIENT_ID = 1L;

  private RequestSubmittingDto requestSubmissionDto;
  private SubmittingService submissionService;
  private ClientDto clientDto;

  @Mock
  private Submission submission;
  @Mock
  private SubmissionRepository submissionRepository;
  @Mock
  private SubmittingAssembler submissionAssembler;
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
    requestSubmissionDto = new RequestSubmittingDto(CLIENT_ID, new Date(), DueTerm.DAYS30, items);

    submissionService = new SubmittingService(submissionAssembler, submissionRepository,
        clientRepository, productRepository);
    clientDto = new ClientDto();

    willReturn(submission).given(submissionAssembler).createSubmission(requestSubmissionDto);
    willReturn(clientDto).given(clientRepository).getClientDto(anyLong());
  }

  @Test
  public void givenRequestSubmissionDtoWhenCreateSubmissionThenVerifySubmissionIsCreatedBysubmissionRepository()
      throws NegativeParameterException {
    submissionService.createSubmission(requestSubmissionDto);

    verify(submissionRepository).createSubmission(submission);
  }

  @Test
  public void givenRequestSubmissionDtoWhenCreateSubmissionThenVerifySubmissionIsCreatedBySubmissionAssembler()
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
  public void givenProductWithNegativeQuantityWhenCreateSubmissionThenThrowNegativeParameterException() {
    willReturn(NEGATIVE_ITEM_QUANTITY).given(item).getQuantity();

    submissionService.createSubmission(requestSubmissionDto);
  }

  @Test
  public void givenRequestWithoutDueTermWhenSetToDefaultIfNeededThenIsSetCorrectly() {
    requestSubmissionDto.setDueTerm(null);

    submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto, IMMEDIATE_DUE_TERM);

    assertEquals(IMMEDIATE_DUE_TERM, requestSubmissionDto.getDueTerm());
  }

  @Test
  public void givenRequestWithDueTermWhenSetToDefaultIfNeededThenDueTermIsNotModified() {
    requestSubmissionDto.setDueTerm(MONTH_AWAY_DUE_TERM);

    submissionService.setDueTermToDefaultIfNeeded(requestSubmissionDto, IMMEDIATE_DUE_TERM);

    assertEquals(MONTH_AWAY_DUE_TERM, requestSubmissionDto.getDueTerm());
  }

}
