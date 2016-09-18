package lingoHigh.service;

import lingoHigh.entry.User;

import java.util.List;

/**
 * Created by DuHongcai on 2016/9/8.
 */
public interface UserService {
    List<User> queryUsers(String userName);
    
    void insertUser(User user);

    void delUser(String name);

    void updateUser(User user);

}
