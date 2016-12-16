package com.derder.common.util;

import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaolei
 * @create 2016-09-18 13:40
 */
public class DateUtil
{

    /**
     * 常用时间格式
     */
    public static final String Format_Date = "yyyy-MM-dd";

    public static final String Format_Time = "HH:mm:ss";

    public static Date string2Date(String str)
    {
        if(Strings.isNullOrEmpty(str))
            return new Date();
        return java.sql.Date.valueOf(str);
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     * 
     * @param date 待被格式化日期
     * @param format 自定义日期格式器
     * @return 格式后的日期字符串
     */
    public static String formatDate(Date date, String format)
    {
        String s = "";

        if(date != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            s = sdf.format(date);
        }

        return s;
    }
}
