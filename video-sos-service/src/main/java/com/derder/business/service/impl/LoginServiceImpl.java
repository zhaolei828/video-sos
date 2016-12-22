package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.model.User;
import com.derder.business.service.LoginService;
import com.derder.business.service.UserService;
import com.derder.common.Constants;
import com.derder.common.redis.CacheService;
import com.derder.common.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 12:19
 * 编码作者：zhaolei
 */
@Service
public class LoginServiceImpl extends BaseDomainService implements LoginService {
    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    @Override
    public String createToken() {
        long id = generateID();
        String token = MD5.sign(Long.toString(id), Constants.MD5_KEY,Constants.SYSTEM_ENCODING);
        return token;
    }

    @Override
    public String login(String phone, String password) {
        User user = userService.checkLogin(phone,password);
        if (null != user){
            String token = createToken();
            cacheService.add(Constants.CACHE_PREFIX_TOKEN + token,user.getId());
            return token;
        }
        return null;
    }
}
