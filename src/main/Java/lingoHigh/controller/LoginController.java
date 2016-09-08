package lingoHigh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DuHongcai on 2016/9/8.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/")
    public String toIndex(){
        return "index";
    }
}





