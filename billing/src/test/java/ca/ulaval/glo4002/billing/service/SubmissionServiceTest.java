package ca.ulaval.glo4002.billing.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.HttpClient;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionServiceTest {

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

  private RequestSubmissionDto requestSubmissionDto;
  private SubmissionService submissionService;

  @Before
  public void setUp() {
    requestSubmissionDto = new RequestSubmissionDto();
    submissionService = new SubmissionService(submissionAssembler, submissionRepository);
  }

  @Test
  public void givenSubmissionServiceWhencreateSubmissionThenVerifyThatAllMethodsHaveBeenCalled() {
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

}
