package com.xlw.levyx.api.controller;

import com.xlw.levyx.api.service.UserService;
import com.xlw.levyx.mapper.model.User;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by levyx on 2016/9/23 0023.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Profiled(tag = "get_user_by_id")
    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User hello(@RequestParam(name="userId",required = true) String userId){
        return userService.findUserById(Long.parseLong(userId));
    }
}
