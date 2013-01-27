/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.base.Strings;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

/**
 *
 * @author Sebastian Sdorra
 */
public class BaseDirectory
{

  /** Field description */
  static final String DIRECTORY_PROPERTY = "shiro-oauth.home";

  /** Field description */
  private static final String DIRECTORY_DEFAULT = ".shiro-oauth";

  /** Field description */
  private static final String DIRECTORY_ENVIRONMENT = "SHIROOAUTH_HOME";

  /** Field description */
  private static final BaseDirectory instance = new BaseDirectory();

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   */
  private BaseDirectory()
  {
    directory = findBaseDirectory();
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param name
   *
   * @return
   */
  public static File directory(String name)
  {
    File dir = file(name);

    if (!dir.exists() &&!dir.mkdirs())
    {
      throw new IllegalStateException("could not create directory");
    }

    return dir;
  }

  /**
   * Method description
   *
   *
   * @param name
   *
   * @return
   */
  public static File file(String name)
  {
    return new File(instance.directory, name);
  }

  /**
   * Method description
   *
   *
   * @param name
   *
   * @return
   */
  public static String path(String name)
  {
    return file(name).getAbsolutePath();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  private File findBaseDirectory()
  {
    String path = System.getProperty(DIRECTORY_PROPERTY);

    if (Strings.isNullOrEmpty(path))
    {
      path = System.getenv(DIRECTORY_ENVIRONMENT);

      if (Strings.isNullOrEmpty(path))
      {
        path = System.getProperty("user.home").concat(File.separator).concat(
          DIRECTORY_DEFAULT);
      }
    }

    File dir = new File(path);

    if (!dir.exists() &&!dir.mkdirs())
    {
      throw new IllegalStateException("could not create directory");
    }

    return dir;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private File directory;
}
