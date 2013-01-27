/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.security;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import sonia.oauth.dao.UserDAO;
import sonia.oauth.entity.User;

//~--- JDK imports ------------------------------------------------------------

import java.util.Set;

/**
 *
 * @author Sebastian Sdorra
 */
public class DefaultRealm extends AuthorizingRealm
{

  /**
   * Constructs ...
   *
   *
   * @param userDAO
   * @param credentialsMatcher
   *
   * @param userDAOProvider
   */
  @Inject
  public DefaultRealm(Provider<UserDAO> userDAOProvider,
    CredentialsMatcher credentialsMatcher)
  {
    this.userDAOProvider = userDAOProvider;
    setCredentialsMatcher(credentialsMatcher);
    setAuthenticationTokenClass(UsernamePasswordToken.class);
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param token
   *
   * @return
   *
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(
    AuthenticationToken token)
    throws AuthenticationException
  {
    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    String username = upToken.getUsername();
    User user = userDAOProvider.get().get(username);

    System.out.println("LOGIN");
    System.out.println(user);

    if (user == null)
    {
      throw new UnknownAccountException("unknown account ".concat(username));
    }

    return new DefaultAccount(user, username);
  }

  /**
   * Method description
   *
   *
   * @param principals
   *
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
    PrincipalCollection principals)
  {
    User user = principals.oneByType(User.class);
    Set<String> roles;

    if (user.isAdmin())
    {
      roles = ImmutableSet.of(Roles.ADMIN, Roles.USER);
    }
    else
    {
      roles = ImmutableSet.of(Roles.USER);
    }

    return new SimpleAuthorizationInfo(roles);
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private Provider<UserDAO> userDAOProvider;
}
