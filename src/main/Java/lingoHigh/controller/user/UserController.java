package lingoHigh.controller.user;

import lingoHigh.entry.User;
import lingoHigh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DuHongcai on 2016/9/9.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/control")
    public String userInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        List<User> user = userService.queryUsers("");
        request.setAttribute("users",user);
        return "/user/userInfo";
    }

    @RequestMapping(value = "/updateUserInfo")
    public void updateUserInfo(HttpServletRequest request,HttpServletResponse response,User newUser){
        userService.updateUser(newUser);
    }
}
