package com.zhangjin.common.result;

import java.io.Serializable;

/**
 * Created by siiiriu on 2020/8/9.
 */
public class BaseResult implements Serializable {

    private int code;

    private String message;

    public BaseResult() {
        code = 200;
        message = "success";
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
