package com.xlw.levyx.api.utils;

import java.util.UUID;

/**
 * Created by levyx on 2017/7/17.
 */
public class CommonUtils {
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
