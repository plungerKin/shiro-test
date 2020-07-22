package com.earth.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {



    @RequestMapping(value = "/login.html")
    public String loginHtml (){
        return "login";
    }

    @RequestMapping(value = "/")
    public String login (){
        return "login";
    }
    @RequestMapping(value = "/index.html")
    public String index (){
        return "index1";
    }
    @RequestMapping(value = "/c_add.html")
    public String cadd (){
        return "c_add";
    }

    @RequestMapping(value = "/lesspermission.html")
    public String lesspermission (){
        return "lesspermission";
    }
}
