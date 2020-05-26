package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class User
{
  private int ID;
  private static User instance;
  private static Lock lock = new ReentrantLock();

  private User(int ID)
  {
    this.ID = ID;
  }

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

  public static User getInstance()
  {
    synchronized (lock)
    {
      return instance;
    }
  }

  public int getUserID()
  {
    return ID;
  }
}
