/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import org.apache.shiro.guice.web.ShiroWebModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sonia.oauth.BaseDirectory;

//~--- JDK imports ------------------------------------------------------------

import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 *
 * @author Sebastian Sdorra
 */
public class BootstrapContextListener extends GuiceServletContextListener
{

  /**
   * the logger for BootstrapContextListener
   */
  private static final Logger logger =
    LoggerFactory.getLogger(BootstrapContextListener.class);

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param servletContextEvent
   */
  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
    super.contextDestroyed(servletContextEvent);

    try
    {
      DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }
    catch (SQLException ex)
    {
      logger.error("could not shutdown database", ex);
    }
  }

  /**
   * Method description
   *
   *
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    this.servletContext = servletContextEvent.getServletContext();
    super.contextInitialized(servletContextEvent);
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  protected Injector getInjector()
  {
    return Guice.createInjector(createJPAModule(), new ServletModule()
    {

      @Override
      protected void configureServlets()
      {
        filter("/*").through(PersistFilter.class);
      }

    }, ShiroWebModule.guiceFilterModule(), new CoreModule(),
      new SecurityModule(servletContext));
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  private String createJDBCUrl()
  {
    StringBuilder url = new StringBuilder("jdbc:derby:");

    url.append(BaseDirectory.path("db"));
    url.append(";create=true");

    return url.toString();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  private JpaPersistModule createJPAModule()
  {
    JpaPersistModule jpa = new JpaPersistModule("shiro-oauth-pu");
    Properties properties = new Properties();

    properties.put("javax.persistence.jdbc.driver",
      "org.apache.derby.jdbc.EmbeddedDriver");
    properties.put("javax.persistence.jdbc.url", createJDBCUrl());
    properties.put("javax.persistence.jdbc.user", "shirooauth");
    properties.put("javax.persistence.jdbc.password", "shiro-oauth-secret");
    properties.put("hibernate.dialect",
      "org.hibernate.dialect.DerbyTenSevenDialect");
    properties.put("hibernate.hbm2ddl.auto", "create");

    return jpa.properties(properties);
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private ServletContext servletContext;
}
