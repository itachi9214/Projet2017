package ca.ulaval.glo4002.billing.service.submission;

import java.util.List;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.api.dto.submission.ResponseSubmissionDto;
import ca.ulaval.glo4002.billing.domain.submision.ClientRepository;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submision.ProductRepository;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

public class SubmissionService {

  private static final int MINIMUM_PRODUCT_QUANTITY = 0;

  private SubmissionAssembler submissionAssembler;
  private SubmissionRepository submissionRepository;
  private ClientRepository clientRepository;
  private ProductRepository productRepository;

  public SubmissionService() {
    this.submissionAssembler = ServiceLocator.getService(SubmissionAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.clientRepository = ServiceLocator.getService(ClientRepository.class);
    this.productRepository = ServiceLocator.getService(ProductRepository.class);
  }

  public SubmissionService(SubmissionAssembler submissionAssembler,
      SubmissionRepository submissionRepository, ClientRepository clientRepository,
      ProductRepository productRepository) {
    this.submissionAssembler = submissionAssembler;
    this.submissionRepository = submissionRepository;
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
  }

  public ResponseSubmissionDto createSubmission(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException, ClientNotFoundException, ProductNotFoundException {
    verifyItemsQuantityIsNotNegative(requestSubmissionDto);
    ClientDto clientDto = getAndVerifyClientExists(requestSubmissionDto.getClientId());
    verifyProductsExist(requestSubmissionDto.getItems());
    setDueTermToDefaultIfNeeded(requestSubmissionDto, clientDto.getDefaultDueTerm());

    Submission submission = submissionAssembler.createSubmission(requestSubmissionDto);
    submissionRepository.createSubmission(submission);

    ResponseSubmissionDto responseSubmissionDto = submissionAssembler
        .createResponseSubmissionDto(submission);
    return responseSubmissionDto;
  }

  private void verifyItemsQuantityIsNotNegative(RequestSubmissionDto requestSubmissionDto)
      throws NegativeParameterException {
    for (OrderedProduct product : requestSubmissionDto.getItems()) {
      if (product.getQuantity() < MINIMUM_PRODUCT_QUANTITY) {
        throw new NegativeParameterException("Product quantity");
      }
    }
  }

  public ClientDto getAndVerifyClientExists(Long clientId) throws ClientNotFoundException {
    ClientDto clientDto = clientRepository.getClientDto(clientId);
    return clientDto;
  }

  public void verifyProductsExist(List<OrderedProduct> products) throws ProductNotFoundException {
    for (OrderedProduct item : products) {
      productRepository.getProductDto(item.getProductId());
    }
  }

  public void setDueTermToDefaultIfNeeded(RequestSubmissionDto requestSubmissionDto,
      DueTerm defaultDueTerm) {
    if (requestSubmissionDto.getDueTerm() == null) {
      requestSubmissionDto.setDueTerm(defaultDueTerm);
    }
  }

}
