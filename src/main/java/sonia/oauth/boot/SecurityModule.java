/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.shiro.guice.web.ShiroWebModule;

import sonia.oauth.security.OAuthRealm;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.ServletContext;

/**
 *
 * @author Sebastian Sdorra
 */
public class SecurityModule extends ShiroWebModule
{

  /**
   * Constructs ...
   *
   *
   * @param servletContext
   */
  SecurityModule(ServletContext servletContext)
  {
    super(servletContext);
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   */
  @Override
  protected void configureShiroWeb()
  {
    bindRealm().to(OAuthRealm.class);
  }
}
