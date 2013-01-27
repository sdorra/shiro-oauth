/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.Maps;
import com.google.inject.servlet.ServletModule;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Map;

/**
 *
 * @author Sebastian Sdorra
 */
public class RestModule extends ServletModule
{

  /**
   * Method description
   *
   */
  @Override
  protected void configureServlets()
  {
    Map<String, String> config = Maps.newHashMap();

    config.put("com.sun.jersey.config.property.packages",
      "sonia.oauth.resources");
    filter("/*").through(GuiceContainer.class, config);
  }
}
