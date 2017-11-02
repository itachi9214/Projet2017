package ca.ulaval.glo4002.payment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.payment.api.resource.PaymentResource;
import ca.ulaval.glo4002.payment.api.resource.filters.EntityManagerContextFilter;
import ca.ulaval.glo4002.payment.infrastructure.EntityManagerProvider;
import ca.ulaval.glo4002.payment.service.PaymentAssembler;
import ca.ulaval.glo4002.payment.service.PaymentService;

public class PaymentServer implements Runnable {

  private static final int PORT = 8282;

  public static void main(String[] args) {
    new PaymentServer().run();
  }

  @Override
  public void run() {
    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
    ResourceConfig packageConfig = new ResourceConfig().packages("ca.ulaval.glo4002.payment");

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
    ServiceLocator.register(new EntityManagerProvider());
    // ServiceLocator.register(new IdentityFactory());
    // ServiceLocator.register(new CrmHttpClient());
    ServiceLocator.register(new PaymentAssembler());
    // ServiceLocator.register(new PaymentHibernateRepository());
    ServiceLocator.register(new PaymentService());

    PaymentResource paymentResource = new PaymentResource();
    ServiceLocator.register(paymentResource);
    packageConfig.register(paymentResource);
  }

}
