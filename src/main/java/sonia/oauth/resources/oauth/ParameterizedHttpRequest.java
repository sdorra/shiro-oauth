/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.resources.oauth;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.collect.Iterators;

//~--- JDK imports ------------------------------------------------------------

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Sebastian Sdorra
 */
public class ParameterizedHttpRequest extends HttpServletRequestWrapper
{

  /**
   * Constructs ...
   *
   *
   * @param request
   * @param parameters
   */
  public ParameterizedHttpRequest(HttpServletRequest request,
    MultivaluedMap<String, String> parameters)
  {
    super(request);
    this.parameters = parameters;
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param name
   *
   * @return
   */
  @Override
  public String getParameter(String name)
  {
    return parameters.getFirst(name);
  }

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  public Map getParameterMap()
  {
    return parameters;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  @Override
  public Enumeration getParameterNames()
  {
    return Iterators.asEnumeration(parameters.keySet().iterator());
  }

  /**
   * Method description
   *
   *
   * @param name
   *
   * @return
   */
  @Override
  public String[] getParameterValues(String name)
  {
    String[] result = null;
    List<String> values = parameters.get(name);

    if (values != null)
    {
      result = values.toArray(new String[values.size()]);
    }

    return result;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private MultivaluedMap<String, String> parameters;
}
