/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.ui;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.view.Viewable;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sonia.oauth.resources.ViewableResource;

/**
 *
 * @author Sebastian Sdorra
 */
@Path("login.html")
public class LoginResource extends ViewableResource
{

  /** Field description */
  private static final String TEMPLATE = "login";

  /** Field description */
  private static final String TITLE = "Login";

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param request
   */
  @Inject
  public LoginResource(HttpServletRequest request)
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
