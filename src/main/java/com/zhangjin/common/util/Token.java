package com.zhangjin.common.util;

/**
 * Created by siiiriu on 2020/8/9.
 */
public class Token {


    private String ip;
    private User user;

    private String hash;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
