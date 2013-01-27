/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.ui;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sonia.oauth.dao.UserDAO;
import sonia.oauth.entity.User;
import sonia.oauth.resources.ViewableResource;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.view.Viewable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sebastian Sdorra
 */
@Path("admin/users.html")
public class UsersResource extends ViewableResource
{

  /** Field description */
  private static final String TEMPLATE = "users";

  /** Field description */
  private static final String TITLE = "Users";

  /**
   * the logger for UsersResource
   */
  private static final Logger logger =
    LoggerFactory.getLogger(UsersResource.class);

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param request
   * @param userDAO
   */
  @Inject
  public UsersResource(HttpServletRequest request, UserDAO userDAO)
  {
    super(request);
    this.userDAO = userDAO;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param username
   * @param password
   * @param admin
   *
   * @return
   */
  //J-
  @POST
  @Produces(MediaType.TEXT_HTML)
  public Viewable createUser(
    @FormParam("username") String username,
    @FormParam("password") String password, 
    @DefaultValue("false") @FormParam("admin") boolean admin)
  //J+
  {
    userDAO.create(new User(username, password, admin));

    return getPage();
  }

  /**
   * Method description
   *
   *
   * @param id
   *
   * @return
   */
  @DELETE
  public void deleteUser(@QueryParam("id") long id)
  {
    userDAO.delete(id);
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
  public Viewable getPage()
  {
    Map<String, Object> environment = createEnvironment(TITLE);
    List<User> users = userDAO.getAll();

    environment.put("users", users);

    return createViewable(TEMPLATE, environment);
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private UserDAO userDAO;
}
