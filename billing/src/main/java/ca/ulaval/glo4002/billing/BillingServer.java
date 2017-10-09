package ca.ulaval.glo4002.billing;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.billing.api.ressource.BillingResource;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;
import ca.ulaval.glo4002.billing.infrastructure.bill.BillInMemory;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionInMemory;
import ca.ulaval.glo4002.billing.service.bill.BillAssembler;
import ca.ulaval.glo4002.billing.service.bill.BillService;
import ca.ulaval.glo4002.billing.service.submission.SubmissionAssembler;
import ca.ulaval.glo4002.billing.service.submission.SubmissionService;

public class BillingServer implements Runnable {

  private static final int PORT = 8181;

  public static void main(String[] args) {
    new BillingServer().run();
  }

  @Override
  public void run() {
	SubmissionAssembler submissionAssembler = new SubmissionAssembler();
	SubmissionRepository submissionRepository = new SubmissionInMemory();
	BillRepository billRepository = new BillInMemory();
	BillAssembler billAssembler = new BillAssembler();
	SubmissionService submissionService = new SubmissionService(submissionAssembler,submissionRepository);
	BillService billService = new BillService(billRepository, billAssembler, submissionRepository);
	BillingResource billingResource = new BillingResource(submissionService,billService);
    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
    ResourceConfig packageConfig = new ResourceConfig().packages("ca.ulaval.glo4002.billing").register(billingResource);
    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, "/*");

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.destroy();
    }
  }

}
