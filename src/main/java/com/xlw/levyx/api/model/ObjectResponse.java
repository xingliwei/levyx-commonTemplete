package com.xlw.levyx.api.model;

import java.io.Serializable;

/**
 * Created by levyx on 2017/7/14.
 */
public class ObjectResponse<T> extends Response{
    private T info;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
