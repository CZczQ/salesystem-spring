package org.cc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author cc
 * @Date 2022/12/15 19:14
 * @PackageName:org.cc.converter
 * @ClassName: DateConverter
 * @Description: TODO
 * @Version 1.0
 */
public class DateConverter implements Converter<String, Date> {

    public Date convert(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
