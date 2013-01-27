/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.entity;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import org.hibernate.annotations.Index;

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

  /** Field description */
  private static final RandomNumberGenerator rng =
    new SecureRandomNumberGenerator();

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   */
  public User() {}

  /**
   * Constructs ...
   *
   *
   * @param username
   * @param password
   * @param admin
   */
  public User(String username, String password, boolean admin)
  {
    this.username = username;
    this.setPassword(password);
    this.admin = admin;
  }

  //~--- set methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param password
   */
  public final void setPassword(String password)
  {
    ByteSource saltSource = rng.nextBytes();
    Sha256Hash shaHash = new Sha256Hash(password, saltSource, 2048);

    this.hash = shaHash.getBytes();
    this.salt = saltSource.getBytes();
  }

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
  @Column(name = "PASSWORD_HASH", length = 32)
  private byte[] hash;

  /** Field description */
  @Id
  @GeneratedValue
  private Long id;

  /** Field description */
  @Column(name = "PASSWORD_SALT", length = 16)
  private byte[] salt;

  /** Field description */
  @Index(name = "index_username")
  private String username;
}
