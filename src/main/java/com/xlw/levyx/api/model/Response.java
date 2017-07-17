package com.xlw.levyx.api.model;

import java.io.Serializable;

/**
 * Created by levyx on 2017/7/14.
 */
public class Response implements Serializable{
    private static final long serialVersionUID = -6443209511723639308L;

    private Integer status = 200;
    private String message = "OK";

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
