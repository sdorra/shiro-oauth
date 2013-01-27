/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package sonia.oauth.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sonia.oauth.entity.User;

//~--- JDK imports ------------------------------------------------------------

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Sebastian Sdorra
 */
public class UserDAO
{

  /**
   * the logger for UserDAO
   */
  private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param em
   */
  @Inject
  public UserDAO(EntityManager em)
  {
    this.em = em;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param user
   */
  public void create(User user)
  {
    try
    {
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
    }
    catch (Exception ex)
    {
      logger.error("could not create user", ex);
      em.getTransaction().rollback();
    }
  }

  /**
   * Method description
   *
   *
   * @param id
   */
  public void delete(long id)
  {
    logger.info("delete user with id {}", id);

    try
    {
      em.getTransaction().begin();
      em.remove(em.find(User.class, id));
      em.getTransaction().commit();
    }
    catch (Exception ex)
    {
      logger.error("could not delete user", ex);
      em.getTransaction().rollback();
    }
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param username
   *
   * @return
   */
  public User get(String username)
  {
    User user = null;

    try
    {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<User> query = cb.createQuery(User.class);
      Root<User> root = query.from(User.class);

      query.select(root).where(cb.equal(root.get("username"), username));

      user = em.createQuery(query).getSingleResult();
    }
    catch (NoResultException ex) {}

    return user;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public List<User> getAll()
  {
    List<User> users = Collections.EMPTY_LIST;

    try
    {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<User> query = cb.createQuery(User.class);

      query.from(User.class);

      users = em.createQuery(query).getResultList();
    }
    catch (NoResultException ex) {}

    return users;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private EntityManager em;
}
