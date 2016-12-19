package com.derder.business.dto;

import java.util.List;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 14:00
 * 编码作者：zhaolei
 */
public class UserDTO {
    String userName;
    String phoneNumber;
    String email;
    List<EmrgContactDTO> emrgContactList;

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

    public List<EmrgContactDTO> getEmrgContactList() {
        return emrgContactList;
    }

    public void setEmrgContactList(List<EmrgContactDTO> emrgContactList) {
        this.emrgContactList = emrgContactList;
    }
}
