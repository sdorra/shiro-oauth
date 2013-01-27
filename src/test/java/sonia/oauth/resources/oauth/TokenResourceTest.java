/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.oauth;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.GrantType;

import org.junit.Test;

import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import org.junit.Ignore;

/**
 *
 * @author Sebastian Sdorra
 */
public class TokenResourceTest extends JerseyTest
{

  /**
   * Constructs ...
   *
   *
   * @throws TestContainerException
   */
  public TokenResourceTest() throws TestContainerException
  {
    super("sonia.oauth.resources");
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @throws OAuthProblemException
   * @throws OAuthSystemException
   */
  @Test @Ignore
  public void testAuthorize() throws OAuthSystemException, OAuthProblemException
  {
    OAuthClient client = new OAuthClient(new URLConnectionClient());
    OAuthClientRequest request = OAuthClientRequest.tokenLocation(
                                   uri("oauth", "token")).setGrantType(
                                   GrantType.PASSWORD).setClientId(
                                   Common.CLIENT_ID).setUsername(
                                   Common.USERNAME).setPassword(
                                   Common.PASSWORD).buildBodyMessage();

    System.out.println(request.getBody());

    OAuthJSONAccessTokenResponse response = client.accessToken(request);

    assertNotNull(response.getAccessToken());
  }

  /**
   * Method description
   *
   *
   * @param parts
   *
   * @return
   */
  private String uri(String... parts)
  {
    String uri = getBaseURI().toString();

    for (String part : parts)
    {
      if (!uri.endsWith("/"))
      {
        uri = uri.concat("/");
      }

      uri = uri.concat(part);
    }

    return uri;
  }
}
