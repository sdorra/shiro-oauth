/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth;

/**
 *
 * @author Sebastian Sdorra
 */
public enum Stage
{

  DEVELOPMENT, PRODUCTION;

  /** Field description */
  private static final Stage currentStage = findCurrent();

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public static Stage current()
  {
    return currentStage;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  private static Stage findCurrent()
  {
    Stage stage = PRODUCTION;

    if (BaseDirectory.file(".development").exists())
    {
      stage = DEVELOPMENT;
    }

    return stage;
  }
}
