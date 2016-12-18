package com.derder.business.model;

import com.derder.base.BaseModel;
import com.derder.common.Constants;
import com.derder.common.emtype.UserGroup;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 14:01
 * 编码作者：zhaolei
 */
@Entity
@Table(name = "user")
@GenericGenerator(name = Constants.SYSTEM_GENERATOR, strategy = Constants.ASSIGNED)
public class User extends BaseModel<Long> {
    private String userName;
    private String userEmail;
    private String userPhone;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(length=1,nullable=false,name = "USER_GROUP")
    private UserGroup userGroup;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
