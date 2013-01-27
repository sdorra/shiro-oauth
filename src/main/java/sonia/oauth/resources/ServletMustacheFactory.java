/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources;

//~--- non-JDK imports --------------------------------------------------------

import com.github.mustachejava.DefaultMustacheFactory;

import com.google.common.base.Charsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//~--- JDK imports ------------------------------------------------------------

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.concurrent.Executors;

import javax.servlet.ServletContext;

/**
 *
 * @author Sebastian Sdorra
 */
public class ServletMustacheFactory extends DefaultMustacheFactory
{

  /** Field description */
  private static final String DIRECTORY_TEMPLATES = "/WEB-INF/pages/";

  /** Field description */
  private static final String EXTENSION = ".mustache";

  /**
   * the logger for ServletMustacheFactory
   */
  private static final Logger logger =
    LoggerFactory.getLogger(ServletMustacheFactory.class);

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param servletContext
   */
  public ServletMustacheFactory(ServletContext servletContext)
  {
    this.servletContext = servletContext;
    this.setExecutorService(Executors.newCachedThreadPool());
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param resourceName
   *
   * @return
   */
  @Override
  public Reader getReader(String resourceName)
  {
    if (!resourceName.endsWith(EXTENSION))
    {
      resourceName = resourceName.concat(EXTENSION);
    }

    if (!resourceName.startsWith(DIRECTORY_TEMPLATES))
    {
      resourceName = DIRECTORY_TEMPLATES.concat(resourceName);
    }

    if (logger.isTraceEnabled())
    {
      logger.trace("try to find resource for {}", resourceName);
    }

    Reader reader = null;
    InputStream is = servletContext.getResourceAsStream(resourceName);

    if (is != null)
    {
      if (logger.isTraceEnabled())
      {
        logger.trace("found resoruce for {}, return reader", resourceName);
      }

      reader = new BufferedReader(new InputStreamReader(is, Charsets.UTF_8));
    }
    else
    {
      logger.warn("could not find resource {}", resourceName);

      throw new RuntimeException(
        "could not find template for resource ".concat(resourceName));
    }

    return reader;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private ServletContext servletContext;
}
