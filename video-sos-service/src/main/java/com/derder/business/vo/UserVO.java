package com.derder.business.vo;

import com.derder.business.emtype.UserGroup;
import com.derder.business.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-17
 * Time: 下午6:32
 */
public class UserVO {
    String userName;
    String phoneNumber;
    String email;
    String password;
    List<EmrgContactVO> emrgContactList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EmrgContactVO> getEmrgContactList() {
        return emrgContactList;
    }

    public void setEmrgContactList(List<EmrgContactVO> emrgContactList) {
        this.emrgContactList = emrgContactList;
    }

    public User convertUser(UserVO userVO){
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
        user.setUserEmail(userVO.getEmail());
        user.setUserGroup(UserGroup.COMMON_USER);
        user.setUserPhone(userVO.getPhoneNumber());
        return  user;
    }
}
