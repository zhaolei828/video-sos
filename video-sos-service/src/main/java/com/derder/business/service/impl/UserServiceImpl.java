package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.dao.EmrgContactDAO;
import com.derder.business.dao.UserDAO;
import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import com.derder.business.service.UserService;
import com.derder.common.util.EnableFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 12:20
 * 编码作者：zhaolei
 */
@Service
public class UserServiceImpl extends BaseDomainService implements UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    EmrgContactDAO emrgContactDAO;

    @Override
    public User checkLogin(String userName, String password) {
        return userDAO.findByUserNameAndPasswordAndEnableFlag(userName,password, EnableFlag.Y);
    }

    @Override
    public void addUserAndEmrgContactList(User user, List<EmrgContact> emrgContactList) {
        Long userId = generateID();
        if (null == user.getCreateBy() || user.getCreateBy() == 0){
            user.setCreateBy(userId);
        }
        if (null == user.getUpdateBy() || user.getUpdateBy() == 0){
            user.setUpdateBy(userId);
        }
        Timestamp now = new Timestamp(new Date().getTime());
        user.setID(userId);
        user.setCreateBy(user.getCreateBy());
        user.setCreateTime(now);
        user.setUpdateBy(user.getUpdateBy());
        user.setUpdateTime(now);
        userDAO.save(user);
        for (EmrgContact emrgContact : emrgContactList){
            Long contactId = generateID();
            if (null == emrgContact.getCreateBy() || emrgContact.getCreateBy() == 0){
                emrgContact.setCreateBy(userId);
            }
            if (null == emrgContact.getUpdateBy() || emrgContact.getUpdateBy() == 0){
                emrgContact.setUpdateBy(userId);
            }
            emrgContact.setID(contactId);
            emrgContact.setCreateBy(emrgContact.getCreateBy());
            emrgContact.setCreateTime(now);
            emrgContact.setUpdateBy(emrgContact.getUpdateBy());
            emrgContact.setUpdateTime(now);
            emrgContact.setBandUser(userId);
        }
        emrgContactDAO.save(emrgContactList);
    }
}
