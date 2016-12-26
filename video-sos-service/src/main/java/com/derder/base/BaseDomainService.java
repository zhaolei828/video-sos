package com.derder.base;


import com.derder.common.tools.IDGenerator;
import com.derder.common.util.JsonUtil;

import java.util.List;

/**
 * 类 编 号：BL_PU6030202_BaseDomainService
 * 类 名 称：BaseDomainService
 * 内容摘要：抽象基础DS,以后扩展使用
 * 完成日期：2016-07-23
 * 编码作者：
 */
public abstract class BaseDomainService
{

    /**
     * 生成实体表编号
     * @return
     */
    protected synchronized static Long generateID()
    {
        return IDGenerator.getWorkId();
    }

    /**
     * @param text 需要转换JSON数据
     * @param clazz JOSN所属类型
     * @return
     */
    protected <T> T parseObject(String text, Class<T> clazz)
    {
        return JsonUtil.parseObject(text, clazz);
    }

    /**
     * 转化为集合对象
     * @param text 需要转换JSON数据
     * @param clazz JOSN所属类型
     * @return
     */
    protected <T> List<T> parseArray(String text, Class<T> clazz)
    {
        return JsonUtil.parseArray(text, clazz);
    }

    protected int commonLimitNum = 10;
}
