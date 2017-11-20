package com.xlw.levyx.api.convert;

import org.apache.commons.lang3.StringUtils;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by levyx on 2017/7/17.
 * 如请求参数中包含时间类型的数据，无法自动映射到Controller里的Date参数
 * 需要使用@initBinder注解为binder提供一个数据的转换器，这个转换器可以自己实现，也可以用spring官方的一些实现。
 */
public class DateConvert extends PropertyEditorSupport {
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        if (StringUtils.isBlank(text)){
            setValue(null);
            return;
        }
        try {
            Date date = format.parse(text);
            setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
