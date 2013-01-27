/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.entity;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Sdorra
 */
@Entity
@Table(name = "OAUTH_USER")
public class User implements Serializable
{

  /** Field description */
  private static final long serialVersionUID = -6532230509150752413L;

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public byte[] getHash()
  {
    return hash;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public Long getId()
  {
    return id;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public byte[] getSalt()
  {
    return salt;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isAdmin()
  {
    return admin;
  }

  //~--- set methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param admin
   */
  public void setAdmin(boolean admin)
  {
    this.admin = admin;
  }

  /**
   * Method description
   *
   *
   * @param hash
   */
  public void setHash(byte[] hash)
  {
    this.hash = hash;
  }

  /**
   * Method description
   *
   *
   * @param salt
   */
  public void setSalt(byte[] salt)
  {
    this.salt = salt;
  }

  /**
   * Method description
   *
   *
   * @param username
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  @Column(name = "ADMINISTRATOR")
  private boolean admin;

  /** Field description */
  @Column(name = "PASSWORD_HASH")
  private byte[] hash;

  /** Field description */
  @Id
  @GeneratedValue
  private Long id;

  /** Field description */
  @Column(name = "PASSWORD_SALT")
  private byte[] salt;

  /** Field description */
  private String username;
}
