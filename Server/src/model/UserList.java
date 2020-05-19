package model;

import java.util.ArrayList;

public class UserList
{
  private ArrayList<User> users;

  public UserList()
  {
    this.users = new ArrayList<>();
  }

  public int getSize()
  {
    return users.size();
  }

  public User getUser(int index)
  {
    return users.get(index);
  }

  public void addUser(User user)
  {
    if (user == null)
    {
      throw new IllegalArgumentException("A null user");
    }
    if (getUserById(user.getId()) != null)
    {
      throw new IllegalArgumentException(
          "User with id " + user.getId() + " already exist");
    }
    users.add(user);
  }

  public User getUserById(int id)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (id == users.get(i).getId())
      {
        return users.get(i);
      }
    }
    return null;
  }

  public User getUserByName(String username)
  {
    if (username != null)
    {
      for (int i = 0; i < users.size(); i++)
      {
        if (username.equalsIgnoreCase(users.get(i).getUsername()))
        {
          return users.get(i);
        }
      }
    }
    return null;
  }
}
