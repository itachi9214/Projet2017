package ca.ulaval.glo4002.payment.infrastructure.payment;

import ca.ulaval.glo4002.payment.ServiceLocator;
import ca.ulaval.glo4002.payment.domain.payment.ClientRepository;
import ca.ulaval.glo4002.payment.http.ClientNotFoundException;
import ca.ulaval.glo4002.payment.http.CrmHttpClient;

public class ClientHttpRepository implements ClientRepository {

  private CrmHttpClient httpClient;

  public ClientHttpRepository() {
    this.httpClient = ServiceLocator.getService(CrmHttpClient.class);
  }

  public ClientHttpRepository(CrmHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public void verifyClientExists(Long clientNumber) throws ClientNotFoundException {
    httpClient.verifyClientExists(clientNumber);
  }

}
