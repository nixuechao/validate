package com.zolvces.demo;

/**
 * @author niXueChao
 * @date 2019/5/15 13:21.
 */
public class ResultBean<T> {

    private Integer code;

    private Object msg;

    private T data;

    public ResultBean() {
    }

    public ResultBean(Integer code, Object msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
