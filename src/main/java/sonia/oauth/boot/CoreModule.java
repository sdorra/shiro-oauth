/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.Maps;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;

import sonia.oauth.dao.UserDAO;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import java.util.Map;

/**
 *
 * @author Sebastian Sdorra
 */
public class CoreModule extends ServletModule
{

  /** Field description */
  private static final String PACKAGE = "sonia.oauth.resources";

  /** Field description */
  private static final String PATTERN_REST_EXCLUDE = "/(resources/).*";

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   */
  @Override
  protected void configureServlets()
  {
    bind(UserDAO.class);

    bind(ApplicationInitializer.class).asEagerSingleton();

    Map<String, String> config = Maps.newHashMap();

    config.put(PackagesResourceConfig.PROPERTY_PACKAGES, PACKAGE);
    config.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX,
      PATTERN_REST_EXCLUDE);
    filter("/*").through(ApplicationInitializer.class);
    filter("/*").through(GuiceContainer.class, config);
  }
}
