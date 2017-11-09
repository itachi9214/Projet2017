package ca.ulaval.glo4002.billing.http;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.payment.PaymentSource;

@RunWith(MockitoJUnitRunner.class)
public class PaymentHttpTest {
  private static final float AMOUNT = 40;
  private static final String LOCALHOST = "http://localhost:8282";
  private static final String PAYMENTS = "/payments/";
  private static final Long EXISTING_CLIENT_NUMBER = 1L;
  private static final String ACCOUNT = "XXXX-XXX-XXX";

  private PaymentHttp paymentHttp;
  private RequestPaymentDto requestPaymentDto;
  private ObjectMapper mapper;

  @Mock
  private UtilHttp utilHttp;
  @Mock
  private Response response;

  @Before
  public void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    requestPaymentDto = new RequestPaymentDto(EXISTING_CLIENT_NUMBER, AMOUNT, ACCOUNT,
        PaymentSource.EFT);
    willReturn(response).given(utilHttp).callUrlWithGetMethod(anyString());
    paymentHttp = new PaymentHttp(utilHttp);
  }

  @Test
  public void givenClientNumberFoundWhenGetClientDtoThenReturnDto() throws IOException {
    String url = LOCALHOST + PAYMENTS;

    paymentHttp.makePayment(requestPaymentDto);

    verify(utilHttp).callUrlWithPostMethod(url, requestPaymentDto);
  }

}
