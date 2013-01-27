/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.ui;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;

import sonia.oauth.resources.ViewableResource;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.view.Viewable;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sebastian Sdorra
 */
@Path("user/index.html")
public class OverviewResource extends ViewableResource
{

  /** Field description */
  private static final String TEMPLATE = "index";

  /** Field description */
  private static final String TITLE = "Overview";

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param request
   */
  @Inject
  public OverviewResource(HttpServletRequest request)
  {
    super(request);
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Viewable getForm()
  {
    return createViewable(TEMPLATE, TITLE);
  }
}
