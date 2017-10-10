package ca.ulaval.glo4002.billing.service.submission;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.CrmHttpClient;
import ca.ulaval.glo4002.billing.http.HttpClient;

public class SubmissionService {

  private static final int MINIMUM_PRODUCT_QUANTITY = 0;
  private SubmissionAssembler submissionAssembler;
  private SubmissionRepository submissionRepository;
  private HttpClient httpClient;

  public SubmissionService() {
    this.submissionAssembler = ServiceLocator.getService(SubmissionAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.httpClient = ServiceLocator.getService(CrmHttpClient.class);
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
        throw new NegativeParameterException("Product quantity");
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
