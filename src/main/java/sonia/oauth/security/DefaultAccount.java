/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.security;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.ImmutableList;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import sonia.oauth.entity.User;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 *
 * @author Sebastian Sdorra
 */
public class DefaultAccount implements SaltedAuthenticationInfo
{

  /** Field description */
  private static final long serialVersionUID = -3810551987457502647L;

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param user
   * @param realm
   */
  public DefaultAccount(User user, String realm)
  {
    this.user = user;
    this.realm = realm;
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  public Object getCredentials()
  {
    return user.getHash();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  public ByteSource getCredentialsSalt()
  {
    return ByteSource.Util.bytes(user.getSalt());
  }

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  public PrincipalCollection getPrincipals()
  {
    List<?> principals = ImmutableList.of(user.getUsername(), user);

    return new SimplePrincipalCollection(principals, realm);
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private String realm;

  /** Field description */
  private User user;
}
