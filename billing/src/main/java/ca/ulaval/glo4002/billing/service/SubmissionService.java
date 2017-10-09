package ca.ulaval.glo4002.billing.service;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.Submission.DueTerm;
import ca.ulaval.glo4002.billing.domain.Submission.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.Submission.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.Submission.Submission;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionAssembler;
import ca.ulaval.glo4002.billing.domain.Submission.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.CrmHttpClient;
import ca.ulaval.glo4002.billing.http.HttpClient;
import ca.ulaval.glo4002.billing.infrastructure.SubmissionInMemory;

public class SubmissionService {

  private static final int MINIMUM_PRODUCT_QUANTITY = 0;
  private SubmissionAssembler submissionAssembler;
  private SubmissionRepository submissionRepository;
  private HttpClient httpClient;

  public SubmissionService() {
    submissionAssembler = new SubmissionAssembler();
    submissionRepository = new SubmissionInMemory();
    httpClient = new CrmHttpClient();
  }

  public SubmissionService(SubmissionAssembler submissionAssembler,
      SubmissionRepository submissionRepository) {
    this.submissionAssembler = submissionAssembler;
    this.submissionRepository = submissionRepository;
    httpClient = new CrmHttpClient();
  }

  public ResponseSubmissionDto createSubmission(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    verifyItemsQuantityIsNotNegative(requestSubmissionDto);

    Submission submission = submissionAssembler.createSubmission(requestSubmissionDto);
    submissionRepository.createSubmission(submission);
    return submissionAssembler.createResponseSubmissionDto(submission);
  }

  public ClientDto getClientByIdInCrm(Long clientId) {
    ClientDto clientDto = httpClient.getClientDto(clientId);
    return clientDto;
  }

  public ProductDto getProductByIdInCrm(Integer productId) {
    ProductDto productDto = httpClient.getProductDto(productId);
    return productDto;
  }

  private void verifyItemsQuantityIsNotNegative(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    for (OrderedProduct product : requestSubmissionDto.getItems()) {
      if (product.getQuantity() < MINIMUM_PRODUCT_QUANTITY) {
        throw new NegativeParameterException();
      }
    }
  }

  public void setDueTermToDefaultIfNeeded(RequestSubmissionDto requestSubmissionDto,
      DueTerm defaultDueTerm) {
    if (requestSubmissionDto.getDueTerm() == null) {
      requestSubmissionDto.setDueTerm(defaultDueTerm);
    }
  }
}
