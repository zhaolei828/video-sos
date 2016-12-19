package com.derder.api;

import com.derder.base.BaseController;
import com.derder.business.model.User;
import com.derder.business.service.UserService;
import com.derder.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaolei on 2016/12/19.
 */
public class BaseApiController extends BaseController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    protected long getUserId(){
        long userId = (Long)request.getAttribute(Constants.USER_ID);
        return userId;
    }

    protected User getUser(){
        long userId = (Long)request.getAttribute(Constants.USER_ID);
        User user = userService.getUser(userId);
        return user;
    }
}
