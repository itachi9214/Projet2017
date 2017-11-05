package ca.ulaval.glo4002.billing.api.resource;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.api.dto.submission.RequestSubmissionDto;
import ca.ulaval.glo4002.billing.domain.submision.DueTerm;
import ca.ulaval.glo4002.billing.domain.submision.NegativeParameterException;
import ca.ulaval.glo4002.billing.domain.submision.OrderedProduct;
import ca.ulaval.glo4002.billing.service.bill.BillService;
import ca.ulaval.glo4002.billing.service.submission.SubmissionService;

@RunWith(MockitoJUnitRunner.class)
public class BillingResourceTest {

  private static final long BILL_NUMBER = 1L;
  private static final Long EXISTING_CLIENT = 2L;

  private BillingResource billingResource;
  private BillForPaymentDto billForPaymentDto;

  @Mock
  private SubmissionService submissionService;
  @Mock
  private BillService billService;
  @Mock
  private OrderedProduct product;

  @Before
  public void setUp() {
    billingResource = new BillingResource(submissionService, billService);
    billForPaymentDto = new BillForPaymentDto();
  }

  @Test
  public void whenCreateSubmissionThenVerifySubmissionIsCreated()
      throws NegativeParameterException {
    List<OrderedProduct> items = new ArrayList<>();
    items.add(product);
    RequestSubmissionDto requestSubmissionDto = new RequestSubmissionDto(EXISTING_CLIENT,
        new Date(), DueTerm.DAYS30, items);

    billingResource.createSubmission(requestSubmissionDto);

    verify(submissionService).createSubmission(requestSubmissionDto);
  }

  @Test
  public void whenCreateBillThenVerifyBillIsCreated() {
    billingResource.createBill(BILL_NUMBER);

    verify(billService).createBill(BILL_NUMBER);
  }

  @Test
  public void whenGetUnpaidBillsThenVerifyGetOldestUnpaidBills() {
    billingResource.getUnpaidBillsOrderedByOldestForClient(EXISTING_CLIENT);

    verify(billService).getOldestUnpaidBillForClient(EXISTING_CLIENT);
  }

  @Test
  public void whenUpdateBillAfterPaymentThenVerifyBillIsUpdated() {
    billingResource.updateBillAfterPayment(billForPaymentDto);

    verify(billService).updateBillAfterPayment(billForPaymentDto);
  }

}
