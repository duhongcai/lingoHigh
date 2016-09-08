package lingoHigh.dao;

import entry.User;

import java.util.List;

/**
 * Created by DuHongcai on 2016/9/7.
 */
public interface UserDao {
    List<User> getUsers(String userName);
}
