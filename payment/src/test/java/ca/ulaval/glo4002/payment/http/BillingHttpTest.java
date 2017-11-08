package ca.ulaval.glo4002.payment.http;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.payment.domain.bill.Bill;
import ca.ulaval.glo4002.payment.infrastructure.bill.BillNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class BillingHttpTest {

  private static final BigDecimal PAID_PRICE = new BigDecimal(1);
  private static final long BILL_NUMBER = 1L;
  private static final long CLIENT_ID = 1L;

  private BillingHttp billingHttp;
  private ObjectMapper mapper;

  @Mock
  private UtilHttp http;
  @Mock
  private Response response;
  @Mock
  private Bill bill;

  @Before
  public void setUp() throws JsonProcessingException {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    billingHttp = new BillingHttp(http);
    bill = new Bill(BILL_NUMBER, PAID_PRICE);
    willReturn(response).given(http).callUrlWithGetMethod(anyString());
    willReturn(mapper.writeValueAsString(bill)).given(response).readEntity(String.class);
  }

  @Test
  public void whenGetOldestUnpaidBillForClientThenReturnBill() throws JsonProcessingException {
    Object result = billingHttp.getOldestUnpaidBillForClient(CLIENT_ID);

    assertTrue(result instanceof Bill);
  }

  @Test(expected = BillNotFoundException.class)
  public void givenNoBillWhenGetOldestUnpaidBillForClientThenThrowsException()
      throws JsonProcessingException {
    bill = null;
    willReturn(mapper.writeValueAsString(bill)).given(response).readEntity(String.class);

    billingHttp.getOldestUnpaidBillForClient(CLIENT_ID);
  }

  @Test
  public void whenGetOldestUnpaidBillForClientThenGetIsCalled() throws JsonProcessingException {
    billingHttp.getOldestUnpaidBillForClient(CLIENT_ID);

    verify(http).callUrlWithGetMethod(anyString());
  }

  @Test
  public void whenUpdateBillAfterPaymentThenPostIsCalled() throws JsonProcessingException {
    billingHttp.updateBillAfterPayment(bill);

    verify(http).callUrlWithPostMethod(anyString(), eq(bill));
  }

}