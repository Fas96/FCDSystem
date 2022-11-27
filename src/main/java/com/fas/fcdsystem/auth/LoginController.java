package com.fas.fcdsystem.auth;

import com.fas.fcdsystem.auth.model.FCDMUser;
import com.fas.fcdsystem.auth.model.UserIdAndPassEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelAndView mv, @RequestParam(value = "userId", required = false) String userId) {

        if (StringUtils.hasLength(userId)) {
            HashMap<String, Object > param = new HashMap<>();
            param.put("isBlocked", false);
            param.put("failCount", 0);
            mv.addObject("user", param);
        }
        //layout/home/index
        mv.setViewName("/login");
        return mv;
    }

    @PostMapping("/api/login/authUser")
    @ResponseBody
    public FCDMUser authenticateUser(HttpServletRequest request,  @RequestBody UserIdAndPassEntity user) {
         //get password and userId from request body
        String userId = user.getUserId();
        String password = user.getPassword();

        return new FCDMUser(userId, password, "name", "email", "phone", "address", "role", "status", "failCount", "isBlocked");
    }
}
