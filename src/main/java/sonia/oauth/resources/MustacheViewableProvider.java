/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources;

//~--- non-JDK imports --------------------------------------------------------

import com.github.mustachejava.MustacheFactory;

import com.google.common.base.Charsets;

import sonia.oauth.Stage;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.template.ViewProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletContext;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Sebastian Sdorra
 */
@Provider
public class MustacheViewableProvider implements ViewProcessor<String>
{

  /**
   * Constructs ...
   *
   *
   * @param servletContext
   */
  public MustacheViewableProvider(@Context ServletContext servletContext)
  {
    this.servletContext = servletContext;
    this.factory = new ServletMustacheFactory(servletContext);
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param path
   *
   * @return
   */
  @Override
  public String resolve(String path)
  {

    // strip classname
    path = path.substring(path.lastIndexOf("/") + 1);

    return path;
  }

  /**
   * Method description
   *
   *
   * @param resolvedPath
   * @param viewable
   * @param out
   *
   * @throws IOException
   */
  @Override
  public void writeTo(String resolvedPath, Viewable viewable, OutputStream out)
    throws IOException
  {

    OutputStreamWriter writer = null;

    try
    {
      writer = new OutputStreamWriter(out, Charsets.UTF_8);
      getFactory().compile(resolvedPath).execute(writer, viewable.getModel());
    }
    finally
    {
      writer.flush();
    }
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  private MustacheFactory getFactory()
  {
    MustacheFactory factory;

    if (Stage.DEVELOPMENT == Stage.current())
    {

      // disable caching for development
      factory = new ServletMustacheFactory(servletContext);
    }
    else
    {
      factory = this.factory;
    }

    return factory;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private MustacheFactory factory;

  /** Field description */
  private ServletContext servletContext;
}
