package com.derder.base;

import com.derder.common.util.ResultData;
import com.derder.common.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 所有服务controller须继承该抽象类
 *
 * @author zhaolei
 * @create 2016-09-08 19:41
 */

public abstract class BaseController
{
    public static int pageNumber = 1;

    public static int pageSize = 10;

    protected <T> ResultData<T> getResultData(boolean succeed, T data, String errorCode, Object... args)
    {
        ResultData<T> ret = new ResultData<T>();
        ret.setSucceed( succeed );
        ret.setErrorCode( errorCode );
        if(StringUtils.isNotBlank(errorCode))
        {
            ret.setErrorMsg( SpringUtil.getMessage(errorCode, args) );
        }
        ret.setData( data );
        return ret;
    }
}
