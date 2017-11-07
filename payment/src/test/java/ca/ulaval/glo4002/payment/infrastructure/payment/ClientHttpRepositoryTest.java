package ca.ulaval.glo4002.payment.infrastructure.payment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.payment.http.CrmHttpClient;

@RunWith(MockitoJUnitRunner.class)
public class ClientHttpRepositoryTest {

  private static final long CLIENT_NUMBER = 1L;

  private ClientHttpRepository clientHttpRepository;

  @Mock
  private CrmHttpClient crmHttpClient;

  @Before
  public void setUp() {
    clientHttpRepository = new ClientHttpRepository(crmHttpClient);
  }

  @Test
  public void whenVerifyClientExistsThenVerifyItsExistence() {
    clientHttpRepository.verifyClientExists(CLIENT_NUMBER);

    verify(crmHttpClient).verifyClientExists(CLIENT_NUMBER);
  }

}
