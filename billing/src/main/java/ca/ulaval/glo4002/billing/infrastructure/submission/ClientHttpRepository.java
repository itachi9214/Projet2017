package ca.ulaval.glo4002.billing.infrastructure.submission;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.domain.submision.ClientRepository;
import ca.ulaval.glo4002.billing.http.CrmHttpClient;

public class ClientHttpRepository implements ClientRepository {

  private CrmHttpClient httpClient;

  public ClientHttpRepository() {
    this.httpClient = ServiceLocator.getService(CrmHttpClient.class);
  }

  public ClientHttpRepository(CrmHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public ClientDto getClientDto(Long clientNumber) {
    return httpClient.getClientDto(clientNumber);
  }

}
