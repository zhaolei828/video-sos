package com.derder.common.util;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 17:29
 * 编码作者：zhaolei
 */
public interface ErrorCode {
    String NO_LOGIN_EXCEPTION = "600001";
    String PARAM_FORMAT_ERROR="100022";

    public String UPLOAD_FILE_CANNOT_EMPTY="100200";
    public String UPLOAD_FILE_EXCEPTION="100201";
    String SEND_EMAIL_EXCEPTION="100203";

    String USERNAME_PASSORD_ERROR="100301";
    String USER_REG_EXCEPTION="100302";
    String USER_NOT_EXIST_EXCEPTION="100303";
}
