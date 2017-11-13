package ca.ulaval.glo4002.payment.domain.payment;

import ca.ulaval.glo4002.payment.http.ClientNotFoundException;

public interface ClientRepository {

  public void verifyClientExists(Long clientNumber) throws ClientNotFoundException;

}
