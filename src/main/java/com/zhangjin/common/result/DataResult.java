package com.zhangjin.common.result;

/**
 * Created by siiiriu on 2020/8/9.
 */
public class DataResult<T> extends BaseResult {

    private T data;


    public DataResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
