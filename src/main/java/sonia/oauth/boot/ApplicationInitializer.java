/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;
import com.google.inject.Provider;


import sonia.oauth.dao.UserDAO;
import sonia.oauth.entity.User;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Sebastian Sdorra
 */
public class ApplicationInitializer implements Filter
{

  /**
   * Constructs ...
   *
   *
   * @param userDAO
   *
   * @param userDAOProvider
   */
  @Inject
  public ApplicationInitializer(Provider<UserDAO> userDAOProvider)
  {
    this.userDAOProvider = userDAOProvider;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   */
  @Override
  public void destroy()
  {

    // do nothing
  }

  /**
   * Method description
   *
   *
   * @param request
   * @param response
   * @param chain
   *
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)
    throws IOException, ServletException
  {
    if (!initialized)
    {
      createAdminAccount(userDAOProvider.get());
    }

    chain.doFilter(request, response);
  }

  /**
   * Method description
   *
   *
   * @param filterConfig
   *
   * @throws ServletException
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException
  {

    // do nothing
  }

  /**
   * Method description
   *
   *
   * @param userDAO
   */
  private void createAdminAccount(UserDAO userDAO)
  {
    User user = userDAO.get("admin");

    if (user == null)
    {
      user = new User();
      user.setUsername("admin");
      user.setAdmin(true);
      userDAO.applyPassword(user, "admin");
      userDAO.create(user);
    }
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private boolean initialized = false;

  /** Field description */
  private Provider<UserDAO> userDAOProvider;
}
