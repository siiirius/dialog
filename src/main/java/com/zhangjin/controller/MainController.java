package com.zhangjin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Controller
public class MainController {



    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }



    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
