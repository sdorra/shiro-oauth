/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sebastian Sdorra
 */
public class Page
{

  /** Field description */
  private static final String RESOURCE_PATH = "/resources";

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param contextPath
   * @param title
   */
  private Page(String contextPath, String title)
  {
    this.contextPath = contextPath;
    this.title = title;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param request
   * @param title
   *
   * @return
   */
  public static Page create(HttpServletRequest request, String title)
  {
    return new Page(request.getContextPath(), title);
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public String getContextPath()
  {
    return contextPath;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getResourcePath()
  {
    return contextPath.concat(RESOURCE_PATH);
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getTitle()
  {
    return title;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private String contextPath;

  /** Field description */
  private String title;
}
