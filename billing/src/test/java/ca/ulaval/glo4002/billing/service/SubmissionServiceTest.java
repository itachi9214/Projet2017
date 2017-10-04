package ca.ulaval.glo4002.billing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
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

  private ClientDto clientDto;
  private ProductDto productDto;
  private RequestSubmissionDto requestSubmissionDto;
  private ResponseSubmissionDto responseSubmissionDto;
  private SubmissionService submissionService;

  @Before
  public void setUp() {
    requestSubmissionDto = new RequestSubmissionDto();
    clientDto = new ClientDto();
    productDto = new ProductDto();
    submissionService = new SubmissionService(submissionAssembler, submissionRepository);

  }

  @Test
  public void givenSubmissionServiceWhencreateSubmissionThenVerifyThatAllMethodsHaveBeenCalled() {
    given(submissionAssembler.createSubmission(requestSubmissionDto)).willReturn(submission);

    ResponseSubmissionDto dto = submissionService.createSubmission(requestSubmissionDto);

    verify(submissionAssembler).createSubmission(requestSubmissionDto);
    verify(submissionRepository).createSubmission(submission);
    assertEquals(dto, responseSubmissionDto);

  }

  @Test
  public void givenSubmissionServiceWhenGetClientByIdCrmThenReturnClientDto() {
    given(httpClient.getClientDto(CLIENT_ID)).willReturn(clientDto);

    ClientDto client = httpClient.getClientDto(CLIENT_ID);

    verify(httpClient).getClientDto(CLIENT_ID);
    assertEquals(client, clientDto);
  }

  @Test
  public void givenSubmissionServiceWhenGetProductByIdCrmThenReturnProductDto() {
    given(httpClient.getProductDto(PRODUCT_ID)).willReturn(productDto);

    ProductDto product = httpClient.getProductDto(PRODUCT_ID);

    verify(httpClient).getProductDto(PRODUCT_ID);
    assertEquals(product, productDto);
  }

}
