package com.xlw.levyx.api.convert;

import org.apache.commons.lang3.StringUtils;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by levyx on 2017/7/17.
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
