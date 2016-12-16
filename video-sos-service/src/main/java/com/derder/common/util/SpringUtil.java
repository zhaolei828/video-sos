package com.derder.common.util;

import org.springframework.context.ApplicationContext;

import java.util.Locale;

/**
 * 类 编 号：
 * 类 名 称：SpringUtil
 * 内容摘要：Spring上下文获取
 * 创建日期：2016/10/14
 * 编码作者：
 */

public abstract class SpringUtil
{
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * @return 获取applicationContext
     */
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * @param name bean的名字
     * @return 指定的bean
     */
    public static Object getBean(String name)
    {
        return applicationContext.getBean( name );
    }

    /**
     * @param code ：对应messages配置的key
     * @return 对应messages配置的value
     */
    public static String getMessage(String code)
    {
        return getMessage( code, new Object[] {} );
    }

    /**
     * @param code 对应messages配置的key
     * @param defaultMessage 没有设置key的时候的默认值
     * @return 对应messages配置的value
     */
    public static String getMessage(String code, String defaultMessage)
    {
        return getMessage( code, null, defaultMessage );
    }

    /**
     * @param code 对应messages配置的key
     * @param defaultMessage 没有设置key的时候的默认值
     * @param locale 所使用的语言
     * @return 对应messages配置的value
     */
    public static String getMessage(String code, String defaultMessage, Locale locale)
    {
        return getMessage( code, null, defaultMessage, locale );
    }

    /**
     * @param code 对应messages配置的key
     * @param locale 所使用的语言
     * @return 对应messages配置的value
     */
    public static String getMessage(String code, Locale locale)
    {
        return getMessage( code, null, "", locale );
    }

    /**
     * @param code ：对应messages配置的key
     * @param args : 数组参数
     * @return 对应messages配置的value
     */
    public static String getMessage(String code, Object... args)
    {
        return getMessage( code, args, "" );
    }

    /**
     * @param code 对应messages配置的key
     * @param args 数组参数
     * @param locale 所有使用的语言
     * @return code对应的值
     */
    public static String getMessage(String code, Object[] args, Locale locale)
    {
        return getMessage( code, args, "", locale );
    }

    /**
     * @param code ：对应messages配置的key
     * @param args : 数组参数
     * @param defaultMessage : 没有设置key的时候的默认值
     * @return 对应messages配置的value
     */
    private static String getMessage(String code, Object[] args, String defaultMessage)
    {
        //这里使用比较方便的方法，不依赖request.
        return getMessage( code, args, defaultMessage, Locale.CHINA  );

    }

    /**
     * 指定语言
     * @param code 对应messages配置的key
     * @param args 数组参数
     * @param defaultMessage 没有设置key的时候的默认值.
     * @param locale 所有使用的语言
     * @return 对应messages配置的value
     */
    private static String getMessage(String code, Object[] args, String defaultMessage, Locale locale)
    {
        return applicationContext.getMessage( code, args, defaultMessage, locale );
    }

}
