package ca.ulaval.glo4002.billing;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.billing.api.resource.BillingResource;
import ca.ulaval.glo4002.billing.api.resource.filters.EntityManagerContextFilter;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.infrastructure.EntityManagerProvider;
import ca.ulaval.glo4002.billing.infrastructure.bill.BillHibernateRepository;
import ca.ulaval.glo4002.billing.infrastructure.bill.CrmHttpClient;
import ca.ulaval.glo4002.billing.infrastructure.bill.CrmHttpProduct;
import ca.ulaval.glo4002.billing.infrastructure.submission.SubmissionHibernateRepository;
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
    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
    ResourceConfig packageConfig = new ResourceConfig().packages("ca.ulaval.glo4002.billing");

    registerServices(packageConfig);

    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, "/*");
    contextHandler.addFilter(EntityManagerContextFilter.class, "/*",
        EnumSet.of(DispatcherType.REQUEST));

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.destroy();
    }
  }

  private void registerServices(ResourceConfig packageConfig) {
    ServiceLocator.register(new IdentityFactory());
    ServiceLocator.register(new EntityManagerProvider());
    ServiceLocator.register(new CrmHttpClient());
    ServiceLocator.register(new CrmHttpProduct());
    ServiceLocator.register(new SubmissionAssembler());
    ServiceLocator.register(new BillAssembler());
    ServiceLocator.register(new SubmissionHibernateRepository());
    ServiceLocator.register(new BillHibernateRepository());
    ServiceLocator.register(new BillService());
    ServiceLocator.register(new SubmissionService());

    BillingResource billingResource = new BillingResource();
    ServiceLocator.register(billingResource);
    packageConfig.register(billingResource);
  }
}
