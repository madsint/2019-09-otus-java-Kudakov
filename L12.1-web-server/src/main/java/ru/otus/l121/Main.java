package ru.otus.l121;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;

import ru.otus.l121.cache.Cache;
import ru.otus.l121.cache.CacheConfiguration;
import ru.otus.l121.cache.CacheFactory;
import ru.otus.l121.dataset.DataSet;
import ru.otus.l121.dataset.DataSetKey;
import ru.otus.l121.dbservice.DBService;
import ru.otus.l121.dbservice.DBServiceHibernateImpl;
import ru.otus.l121.servlet.*;

public class Main {

	private static final int PORT = 8080;
	private static final String RESOURCE_BASE_DIR = "html";

	public static void main(String[] args) throws Exception {

		Cache<DataSetKey, DataSet> cache = CacheFactory.getCache(new CacheConfiguration());
		DBService dbService = new DBServiceHibernateImpl(cache);

		DBHelper.startDB(dbService);

		Server server = new Server(PORT);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setResourceBase(RESOURCE_BASE_DIR);
		context.setContextPath("/");

		configureJspSupport(context);

		context.addServlet(LoginServlet.class, "/login");
		context.addServlet(new ServletHolder(new CacheInfoServlet(cache)), "/cache-info");
		context.addServlet(DefaultServlet.class, "/");

		server.setHandler(context);
		server.start();
		server.join();
	}

	private static void configureJspSupport(ServletContextHandler context) throws IOException {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");

		if (!scratchDir.exists()) {
			if (!scratchDir.mkdirs()) {
				throw new IOException("Unable to create scratch directory: " + scratchDir);
			}
		}
		context.setAttribute("javax.servlet.context.tempdir", scratchDir);

		ClassLoader jspClassLoader = new URLClassLoader(new URL[0], Main.class.getClassLoader());
		context.setClassLoader(jspClassLoader);

		context.addBean(new JspStarter(context));

		ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
		holderJsp.setInitOrder(0);
		holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
		holderJsp.setInitParameter("fork", "false");
		holderJsp.setInitParameter("xpoweredBy", "false");
		holderJsp.setInitParameter("compilerTargetVM", "1.8");
		holderJsp.setInitParameter("compilerSourceVM", "1.8");
		holderJsp.setInitParameter("keepgenerated", "true");
		context.addServlet(holderJsp, "*.jsp");
	}
}

class JspStarter extends AbstractLifeCycle implements ServletContextHandler.ServletContainerInitializerCaller {

	JettyJasperInitializer initializer;
	ServletContextHandler context;

	public JspStarter(ServletContextHandler context) {
		this.initializer = new JettyJasperInitializer();
		this.context = context;
	}

	@Override
	protected void doStart() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(context.getClassLoader());
		try {
			initializer.onStartup(null, context.getServletContext());
			super.doStart();
		} finally {
			Thread.currentThread().setContextClassLoader(classLoader);
		}
	}
}