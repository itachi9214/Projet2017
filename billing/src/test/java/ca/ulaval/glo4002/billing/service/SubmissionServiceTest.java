package ca.ulaval.glo4002.billing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.HttpClient;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionServiceTest {

  private static final DueTerm MONTH_AWAY_DUE_TERM = DueTerm.DAYS30;
  private static final DueTerm IMMEDIATE_DUE_TERM = DueTerm.IMMEDIATE;
  private static final int NEGATIVE_ITEM_QUANTITY = -6;
  private static final Long CLIENT_ID = 10L;
  private static final Integer PRODUCT_ID = 20;

  @Mock
  Submission submission;
  @Mock
  SubmissionRepository submissionRepository;
  @Mock
  SubmissionAssembler submissionAssembler;
  @Mock
  HttpClient httpClient;
  @Mock
  OrderedProduct item;

  private RequestSubmissionDto requestSubmissionDto;
  private SubmissionService submissionService;

  @Before
  public void setUp() {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(item);
    requestSubmissionDto = new RequestSubmissionDto(CLIENT_ID, new Date(), DueTerm.DAYS30, items);

    submissionService = new SubmissionService(submissionAssembler, submissionRepository);
  }

  @Test
  public void givenSubmissionServiceWhenCreateSubmissionThenVerifyThatAllMethodsHaveBeenCalled()
      throws NegativeParameterException {
    given(submissionAssembler.createSubmission(requestSubmissionDto)).willReturn(submission);

    submissionService.createSubmission(requestSubmissionDto);

    verify(submissionAssembler).createSubmission(requestSubmissionDto);
    verify(submissionRepository).createSubmission(submission);
  }

  @Test
  public void givenSubmissionServiceWhenGetClientByIdCrmThenReturnClientDto() {
    httpClient.getClientDto(CLIENT_ID);

    verify(httpClient).getClientDto(CLIENT_ID);
  }

  @Test
  public void givenSubmissionServiceWhenGetProductByIdCrmThenReturnProductDto() {
    httpClient.getProductDto(PRODUCT_ID);

    verify(httpClient).getProductDto(PRODUCT_ID);
  }

  @Test(expected = NegativeParameterException.class)
  public void givenProductWithNegativeQuantityWhenCreateSubmissionThenThrowException() {
    given(item.getQuantity()).willReturn(NEGATIVE_ITEM_QUANTITY);

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
