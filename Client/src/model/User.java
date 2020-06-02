package model;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class representing  a user
 *
 * @author Edvinas Andrijauskas
 * @version 1.0 - May 2020
 */

public class User implements Serializable
{
  private int ID;
  private static User instance;
  private static Lock lock = new ReentrantLock();

  /**
   * One-argument constructor
   *
   * @param ID user id
   */
  private User(int ID)
  {
    this.ID = ID;
  }

  /**
   * Getter for singleton instance
   *
   * @param ID we are getting a User with set id
   */
  public static void getInstance(int ID)
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new User(ID);
        }
      }
    }
  }

  /**
   * Getter for singleton instance
   *
   * @return getting user
   */
  public static User getInstance()
  {
    synchronized (lock)
    {
      return instance;
    }
  }

  /**
   * Getter for user id
   *
   * @return Id as an integer
   */
  public int getUserID()
  {
    return ID;
  }
}
