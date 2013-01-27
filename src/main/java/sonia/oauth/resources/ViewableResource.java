/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.Maps;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.view.Viewable;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sebastian Sdorra
 */
public class ViewableResource
{

  /**
   * Constructs ...
   *
   *
   * @param request
   */
  public ViewableResource(HttpServletRequest request)
  {
    this.request = request;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param title
   *
   * @return
   */
  public Map<String, Object> createEnvironment(String title)
  {
    Map<String, Object> environment = Maps.newHashMap();

    environment.put("page", Page.create(request, title));
    environment.put("subject", SubjectWrapper.create());

    return environment;
  }

  /**
   * Method description
   *
   *
   * @param template
   * @param title
   *
   * @return
   */
  public Viewable createViewable(String template, String title)
  {
    Map<String, Object> environment = createEnvironment(title);

    return createViewable(template, environment);
  }

  /**
   * Method description
   *
   *
   * @param template
   * @param environment
   *
   * @return
   */
  public Viewable createViewable(String template,
    Map<String, Object> environment)
  {
    return new Viewable(template, environment);
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private HttpServletRequest request;
}
