package ca.ulaval.glo4002.application;

import ca.ulaval.glo4002.billing.BillingServer;
import ca.ulaval.glo4002.crm.CrmServer;
import ca.ulaval.glo4002.payment.PaymentServer;

public class ApplicationServer {

  public static void main(String[] args) throws Exception {
    Thread crm = new Thread(new CrmServer(args));
    Thread billing = new Thread(new BillingServer());
    Thread payment = new Thread(new PaymentServer());
    billing.start();
    crm.start();
    payment.start();
  }

  public ApplicationServer(String[] args) {

  }
}
