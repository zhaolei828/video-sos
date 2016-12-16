package com.derder.business.service;

import com.derder.business.model.User;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 11:40
 * 编码作者：zhaolei
 */
public interface UserService {

    User getUserByToken(String token);
}
