package lingoHigh.User;


import lingoHigh.BasicTest;
import lingoHigh.entry.User;
import lingoHigh.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DuHongcai on 2016/9/9.
 */
public class UserTest extends BasicTest {
    @Autowired
    private UserService userService;

    @Test
    public void queryUser(){
        List<User> uses = userService.queryUsers("A");
        System.out.println(uses.size());
    }
}
