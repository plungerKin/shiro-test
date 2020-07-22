package com.earth.shiro.controller;

import com.earth.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test (){
        return "!!!!!!";
    }


    @RequestMapping("/user/login")
    public String login (
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "passWord") String passWord,
            @RequestParam(value = "rememberMe" , defaultValue = "false") boolean rememberMe
    ){
        try {
            userService.checkLogin(userName , passWord , rememberMe);
            System.out.println("-----登录成功-----");
            return "index";
        } catch (Exception e) {
            System.out.println("-----登录失败-----");
            return "login";
        }
    }
}
