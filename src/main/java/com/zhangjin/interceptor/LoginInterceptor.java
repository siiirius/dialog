package com.zhangjin.interceptor;

import com.zhangjin.common.util.KeyUtil;
import com.zhangjin.common.util.LoginUtil;
import com.zhangjin.common.util.Token;
import com.zhangjin.common.util.TokenProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by siiiriu on 2020/8/9.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TokenProcessor.TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    Token token = decodeToken(cookie.getValue(), response);
                    if (token == null) {
                        response.sendRedirect("/login");
                        return false;
                    } else if (!request.getRemoteAddr().equals(token.getIp())) {
                        response.sendRedirect("/login");
                        return false;
                    } else {
                        LoginUtil.setUser(token.getUser());
                    }
                    return true;
                }
            }
        }

        response.sendRedirect("/login");
        return false;
    }

    private Token decodeToken(String value, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        Token decode = TokenProcessor.decode(value, KeyUtil.getKey());

        if (decode == null) {
            decode = TokenProcessor.decode(value, KeyUtil.getLastKey());
            if (decode == null) {
                return null;
            } else {
                try {
                    response.addCookie(new Cookie(TokenProcessor.TOKEN_COOKIE_NAME, TokenProcessor.encode(decode, KeyUtil.getKey())));
                } catch (Exception e) {
                    return null;
                }
            }
        }

        if (!TokenProcessor.checkHash(decode)) {
            return null;
        }
        return decode;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LoginUtil.remove();
    }
}
