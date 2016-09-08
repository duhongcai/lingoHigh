package lingoHigh.service.impl;

import lingoHigh.dao.UserDao;
import lingoHigh.entry.User;
import lingoHigh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DuHongcai on 2016/9/8.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> queryUsers(String userName) {
        List<User> users = userDao.queryUsers(userName);
        if (users.size() == 0){
            return null;
        }
        return users;
    }
}
