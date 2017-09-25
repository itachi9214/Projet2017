package ca.ulaval.glo4002.billing.domain.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.billing.api.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.domain.BillAssembler;
import ca.ulaval.glo4002.billing.domain.DueTerm;

public class BillTest {

  private static final Long clientId = 1L;
  private static final Date creationDate = new Date();
  private static final DueTerm dueTerm = DueTerm.DAYS30;
  private static final List<OrderedProduct> items = new ArrayList<>();
  private static final int productId = 1;
  private static final float price = 50f;
  private static final String note = "product's note";
  private static final int quantity = 3;
  private Bill bill;

  @Before
  public void setUp() {
    items.add(new OrderedProduct(productId, price, note, quantity));
    RequestBillDto requestBillDto = new RequestBillDto();
    requestBillDto.setClientId(clientId);
    requestBillDto.setCreationDate(creationDate);
    requestBillDto.setDueTerm(dueTerm);
    requestBillDto.setItems(items);

    BillAssembler billAssembler = new BillAssembler();
    bill = billAssembler.create(requestBillDto);
  }

  @Test
  public void whenCalculateBillThenReturnCorrectResult() {
    BigDecimal result = bill.calculateBill();
    assertEquals(new BigDecimal(150), result);
  }

}
