/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.oauth;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.OAuthResponse;
import org.apache.amber.oauth2.ext.dynamicreg.server.request
  .JSONHttpServletRequestWrapper;
import org.apache.amber.oauth2.ext.dynamicreg.server.request
  .OAuthServerRegistrationRequest;
import org.apache.amber.oauth2.ext.dynamicreg.server.response
  .OAuthServerRegistrationResponse;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian Sdorra
 */
@Path("oauth/registration")
public class RegistrationResource
{

  /**
   * Method description
   *
   *
   * @param request
   *
   * @return
   *
   * @throws OAuthSystemException
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response register(@Context HttpServletRequest request)
    throws OAuthSystemException
  {

    OAuthServerRegistrationRequest oauthRequest = null;

    try
    {
      oauthRequest = new OAuthServerRegistrationRequest(
        new JSONHttpServletRequestWrapper(request));
      oauthRequest.discover();
      oauthRequest.getClientName();
      oauthRequest.getClientUrl();
      oauthRequest.getClientDescription();
      oauthRequest.getRedirectURI();

      OAuthResponse response = OAuthServerRegistrationResponse.status(
                                 HttpServletResponse.SC_OK).setClientId(
                                 CommonExt.CLIENT_ID).setClientSecret(
                                 CommonExt.CLIENT_SECRET).setIssuedAt(
                                 CommonExt.ISSUED_AT).setExpiresIn(
                                 CommonExt.EXPIRES_IN).buildJSONMessage();

      return Response.status(response.getResponseStatus()).entity(
        response.getBody()).build();

    }
    catch (OAuthProblemException e)
    {
      OAuthResponse response = OAuthServerRegistrationResponse.errorResponse(
                                 HttpServletResponse.SC_BAD_REQUEST).error(
                                 e).buildJSONMessage();

      return Response.status(response.getResponseStatus()).entity(
        response.getBody()).build();
    }

  }
}
