/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import sonia.oauth.security.Roles;

/**
 *
 * @author Sebastian Sdorra
 */
public class SubjectWrapper
{

  /**
   * Constructs ...
   *
   *
   * @param subject
   */
  private SubjectWrapper(Subject subject)
  {
    this.subject = subject;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public static SubjectWrapper create()
  {
    return new SubjectWrapper(SecurityUtils.getSubject());
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isAdmin()
  {
    return subject.hasRole(Roles.ADMIN);
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isAuthenticated()
  {
    return subject.isAuthenticated();
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private Subject subject;
}
