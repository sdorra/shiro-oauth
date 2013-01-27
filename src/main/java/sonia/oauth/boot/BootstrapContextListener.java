/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

import org.apache.shiro.guice.web.ShiroWebModule;

import sonia.oauth.BaseDirectory;

//~--- JDK imports ------------------------------------------------------------

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

    return Guice.createInjector(createJPAModule(), new RestModule(),
      new SecurityModule(servletContext), ShiroWebModule.guiceFilterModule());
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
