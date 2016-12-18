package com.derder.business.dao;

import com.derder.base.BaseDAO;
import com.derder.business.model.User;
import com.derder.common.util.EnableFlag;

/**
 * Created by Administrator on 2016/12/18.
 */
public interface UserDAO extends BaseDAO<User, Long> {
    User findByUserNameAndPasswordAndEnableFlag(String userName, String password, EnableFlag enableFlag);
}
