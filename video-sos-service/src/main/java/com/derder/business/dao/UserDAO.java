package com.derder.business.dao;

import com.derder.base.BaseDAO;
import com.derder.business.model.User;
import com.derder.common.util.EnableFlag;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public interface UserDAO extends BaseDAO<User, Long> {
    User findByUserPhoneAndPasswordAndEnableFlag(String userPhone, String password, EnableFlag enableFlag);

    List<User> findByUserPhoneAndEnableFlag(String userPhone, EnableFlag enableFlag);

    User findByIdAndEnableFlag(Long id, EnableFlag enableFlag);
}
