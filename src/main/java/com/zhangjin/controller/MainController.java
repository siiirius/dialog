package com.zhangjin.controller;

import com.zhangjin.common.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Controller
public class MainController {


    @RequestMapping("/register")
    public String registerPage() {
        return "/register";
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "/login";
    }


    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addAllObjects(getModel());
        modelAndView.setViewName("/index");
        return modelAndView;
    }


    public Map<String, Object> getModel() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", LoginUtil.getUesr().getName());
        return map;
    }
}
