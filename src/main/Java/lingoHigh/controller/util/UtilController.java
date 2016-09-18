package lingoHigh.controller.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DuHongcai on 2016/9/14.
 */
@Controller
@RequestMapping(value = "/test")
public class UtilController {

    @RequestMapping(value = "/")
    public String toTestPage(){
        return "test";
    }
}
