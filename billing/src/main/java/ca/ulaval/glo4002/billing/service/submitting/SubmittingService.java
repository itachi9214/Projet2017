package ca.ulaval.glo4002.billing.service.submitting;

import java.util.List;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.submitting.RequestSubmittingDto;
import ca.ulaval.glo4002.billing.api.dto.submitting.ResponseSubmittingDto;
import ca.ulaval.glo4002.billing.domain.submitting.ClientRepository;
import ca.ulaval.glo4002.billing.domain.submitting.DueTerm;
import ca.ulaval.glo4002.billing.domain.submitting.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submitting.OrderedProduct;
import ca.ulaval.glo4002.billing.domain.submitting.ProductRepository;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;
import ca.ulaval.glo4002.billing.http.ClientNotFoundException;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

public class SubmittingService {

  private static final int MINIMUM_PRODUCT_QUANTITY = 0;

  private SubmittingAssembler submissionAssembler;
  private SubmissionRepository submissionRepository;
  private ClientRepository clientRepository;
  private ProductRepository productRepository;

  public SubmittingService() {
    this.submissionAssembler = ServiceLocator.getService(SubmittingAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.clientRepository = ServiceLocator.getService(ClientRepository.class);
    this.productRepository = ServiceLocator.getService(ProductRepository.class);
  }

  public SubmittingService(SubmittingAssembler submissionAssembler,
      SubmissionRepository submissionRepository, ClientRepository clientRepository,
      ProductRepository productRepository) {
    this.submissionAssembler = submissionAssembler;
    this.submissionRepository = submissionRepository;
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
  }

  public ResponseSubmittingDto createSubmission(RequestSubmittingDto requestSubmissionDto)
      throws NegativeParameterException, ClientNotFoundException, ProductNotFoundException {
    verifyItemsQuantityIsNotNegative(requestSubmissionDto);
    ClientDto clientDto = getAndVerifyClientExists(requestSubmissionDto.getClientId());
    verifyProductsExist(requestSubmissionDto.getItems());
    setDueTermToDefaultIfNeeded(requestSubmissionDto, clientDto.getDefaultDueTerm());

    Submission submission = submissionAssembler.createSubmission(requestSubmissionDto);
    submissionRepository.createSubmission(submission);

    ResponseSubmittingDto responseSubmissionDto = submissionAssembler
        .createResponseSubmissionDto(submission);
    return responseSubmissionDto;
  }

  private void verifyItemsQuantityIsNotNegative(RequestSubmittingDto requestSubmissionDto)
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

  public void setDueTermToDefaultIfNeeded(RequestSubmittingDto requestSubmissionDto,
      DueTerm defaultDueTerm) {
    if (requestSubmissionDto.getDueTerm() == null) {
      requestSubmissionDto.setDueTerm(defaultDueTerm);
    }
  }

}
