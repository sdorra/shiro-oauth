/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sonia.oauth.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author Sebastian Sdorra
 */
public class OAuthRealm extends AuthorizingRealm
{

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
