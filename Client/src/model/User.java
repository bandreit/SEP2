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

  public static User getInstance(int ID)
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
    return instance;
  }

  public static User getInstance()
  {
    return instance;
  }

  public int getUserID()
  {
    return ID;
  }
}
