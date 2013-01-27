/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.boot;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.name.Named;
import com.google.inject.name.Names;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.guice.web.ShiroWebModule;

import sonia.oauth.security.DefaultRealm;
import sonia.oauth.security.Roles;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.ServletContext;

/**
 *
 * @author Sebastian Sdorra
 */
public class SecurityModule extends ShiroWebModule
{

  /** Field description */
  private static final String ATTRIBUTE_FAILURE = "shiroLoginFailure";

  /** Field description */
  private static final String HASH_ALGORITHM = "SHA-256";

  /** Field description */
  private static final int HASH_ITERATIONS = 2048;

  /** Field description */
  private static final String PAGE_LOGIN = "/login.html";

  /** Field description */
  private static final String PAGE_SUCCESS = "/user/index.html";

  /** Field description */
  private static final String PAGE_UNAUTHORIZED = "/error/unauthorized.html";

  /** Field description */
  private static final String PARAM_PASSWORD = "password";

  /** Field description */
  private static final String PARAM_REMEMBERME = "rememberme";

  /** Field description */
  private static final String PARAM_USERNAME = "username";

  /** Field description */
  private static final String PATTERN_ADMIN = "/admin/**";

  /** Field description */
  private static final String PATTERN_USER = "/user/**";

  /** Field description */
  private static final Named NAMED_USERNAMEPARAM =
    Names.named("shiro.usernameParam");

  /** Field description */
  private static final Named NAMED_UNAUTHORIZEDURL =
    Names.named("shiro.unauthorizedUrl");

  /** Field description */
  private static final Named NAMED_SUCCESSURL = Names.named("shiro.successUrl");

  /** Field description */
  private static final Named NAMED_REMEMBERMEPARAM =
    Names.named("shiro.rememberMeParam");

  /** Field description */
  private static final Named NAMED_PASSWORDPARAM =
    Names.named("shiro.passwordParam");

  /** Field description */
  private static final Named NAMED_LOGINURL = Names.named("shiro.loginUrl");

  /** Field description */
  private static final Named NAMED_FAILUREKEYATTRIBUTE =
    Names.named("shiro.failureKeyAttribute");

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param servletContext
   */
  SecurityModule(ServletContext servletContext)
  {
    super(servletContext);
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   */
  @Override
  protected void configureShiroWeb()
  {
    bindConstants();
    bindCredentialsMatcher();

    // bind cache manager
    // bind(CacheManager.class).toProvider(CacheManagerProvider.class);

    // bind realm
    bindRealm().to(DefaultRealm.class);

    // add filters
    addFilterChain(PAGE_LOGIN, AUTHC);
    addFilterChain(PATTERN_USER, AUTHC);
    addFilterChain(PATTERN_ADMIN, AUTHC, config(ROLES, Roles.ADMIN));
  }

  /**
   * Method description
   *
   */
  private void bindConstants()
  {
    bindConstant().annotatedWith(NAMED_LOGINURL).to(PAGE_LOGIN);
    bindConstant().annotatedWith(NAMED_USERNAMEPARAM).to(PARAM_USERNAME);
    bindConstant().annotatedWith(NAMED_PASSWORDPARAM).to(PARAM_PASSWORD);
    bindConstant().annotatedWith(NAMED_REMEMBERMEPARAM).to(PARAM_REMEMBERME);
    bindConstant().annotatedWith(NAMED_SUCCESSURL).to(PAGE_SUCCESS);
    bindConstant().annotatedWith(NAMED_UNAUTHORIZEDURL).to(PAGE_UNAUTHORIZED);
    bindConstant().annotatedWith(NAMED_FAILUREKEYATTRIBUTE).to(
      ATTRIBUTE_FAILURE);
  }

  /**
   * Method description
   *
   */
  private void bindCredentialsMatcher()
  {
    HashedCredentialsMatcher matcher =
      new HashedCredentialsMatcher(HASH_ALGORITHM);

    matcher.setHashIterations(HASH_ITERATIONS);
    matcher.setStoredCredentialsHexEncoded(false);
    bind(CredentialsMatcher.class).toInstance(matcher);
  }
}
