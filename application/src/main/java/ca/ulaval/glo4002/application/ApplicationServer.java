package ca.ulaval.glo4002.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.billing.BillingServer;
import ca.ulaval.glo4002.crm.CrmServer;

public class ApplicationServer implements Runnable {

	private String[] args = { "" };
	private static final int PORT = 8282;

	public static void main(String[] args) throws Exception {
		new ApplicationServer().run();
	}

	public ApplicationServer() {
		Thread crm = new Thread(new CrmServer(args));
		Thread billing = new Thread(new BillingServer());
		billing.start();
		crm.start();
	}

	@Override
	public void run() {
		Server server = new Server(PORT);
		ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
		ResourceConfig packageConfig = new ResourceConfig().packages("ca.ulaval.glo4002.application");
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
