package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;

public interface ClientRepository {

  public ClientDto getClientDto(Long clientNumber);

}