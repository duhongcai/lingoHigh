package lingoHigh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DuHongcai on 2016/9/9.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/control")
    public String userInfo(){

        return "/user/userInfo";
    }
}
