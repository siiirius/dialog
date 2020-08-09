package com.zhangjin.controller;

import com.zhangjin.common.result.BaseResult;
import com.zhangjin.common.result.DataResult;
import com.zhangjin.dal.entity.UserEntity;
import com.zhangjin.servvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Controller
@RequestMapping("/api/user/")
public class UserController {


    @Autowired
    UserService registerService;


    @PostMapping("register")
    public ModelAndView register(String name, String password) {
        ModelAndView modelAndView = new ModelAndView();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            modelAndView.addObject("msg", "name or password is empty.");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        DataResult<UserEntity> dataResult = registerService.register(userEntity);

        modelAndView.setViewName("index");
        return modelAndView;
    }


    @PostMapping("login")
    public ModelAndView login(String name, String password, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            modelAndView.addObject("msg", "name or password is empty.");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        BaseResult login = registerService.login(userEntity);

        if (login.getCode() != 200) {
            modelAndView.setViewName("error");
            modelAndView.addObject("msg", login.getMessage());
            return modelAndView;
        }
        Cookie cookie = new Cookie("token", name);
        response.addCookie(cookie);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
