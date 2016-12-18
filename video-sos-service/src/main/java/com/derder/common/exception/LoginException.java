package com.derder.common.exception;

/**
 *
 * @Description:登录异常处理
 *
 * @author zhaolei
 *
 * @date 2016年12月17日
 *
 */
public class LoginException extends ApecRuntimeException
{
    public LoginException(String errorCode, Object... args)
    {
        super( errorCode, args );
    }

    public LoginException(String errorCode, String message, Object... arguments)
    {
        super( errorCode, message, arguments );
    }

    public LoginException(String errorCode, Throwable ex, Object... arguments)
    {
        super( errorCode, ex, arguments );
    }

    public LoginException(String errorCode, String message, Throwable e, Object... arguments)
    {
        super( errorCode, message, e, arguments );
    }

    public String toString()
    {
        return super.toString();
    }
}
