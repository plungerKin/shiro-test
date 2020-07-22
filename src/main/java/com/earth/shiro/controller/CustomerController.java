package com.earth.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping("/list")
    @RequiresPermissions("sys:k:find")
    public String list(){
        System.out.println("-------查询用户信息");
        return "customer_list";
    }
}
