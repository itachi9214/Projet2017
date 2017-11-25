package ca.ulaval.glo4002.billing.infrastructure.submitting;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.http.CrmHttpClient;
import ca.ulaval.glo4002.billing.infrastructure.submitting.ClientHttpRepository;

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
  public void whenGetClientDtoThenVerifyClientIsFound() {
    clientHttpRepository.getClientDto(CLIENT_NUMBER);

    verify(crmHttpClient).getClientDto(CLIENT_NUMBER);
  }

}
