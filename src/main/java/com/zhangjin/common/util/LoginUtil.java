package com.zhangjin.common.util;

/**
 * Created by siiiriu on 2020/8/10.
 */
public class LoginUtil {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static User getUesr() {
        return threadLocal.get();
    }

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
