package com.derder.business.service;

import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 11:40
 * 编码作者：zhaolei
 */
public interface UserService {

    User checkLogin(String phone,String password);

    void addUserAndEmrgContactList(User user, List<EmrgContact> emrgContactList);

    void updateUserAndEmrgContactList(User user, List<EmrgContact> emrgContactList);

    User getUser(long userId);

    List<EmrgContact> getEmrgContactListByUser(long userId);

    Page<User> listBySearch(String userNameKw, String phoneNumber, String email, String startDate, String endDate, PageRequest pageRequest);
}
