package com.derder.common.util;

import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import com.derder.business.vo.EmrgContactVO;
import com.derder.business.vo.UserVO;
import com.derder.business.emtype.UserGroup;

/**
 * Created by Administrator on 2016/12/18.
 */
public class BeanUtil {
    public static EmrgContact convertEmrgContact(EmrgContactVO emrgContactVO){
        EmrgContact emrgContact = new EmrgContact();
        emrgContact.setName(emrgContactVO.getName());
        emrgContact.setEmail(emrgContactVO.getEmail());
        emrgContact.setPhone(emrgContactVO.getPhoneNumber());
        return  emrgContact;
    }

    public static User convertUser(UserVO userVO){
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setUserEmail(userVO.getEmail());
        user.setUserGroup(UserGroup.COMMON_USER);
        user.setUserPhone(userVO.getPhoneNumber());
        return  user;
    }
}
