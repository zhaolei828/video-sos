package com.derder.business.vo;

import com.derder.business.model.EmrgContact;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-17
 * Time: 下午6:34
 */
public class EmrgContactVO {
    long id;
    String name;
    String email;
    String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmrgContact convertEmrgContact(EmrgContactVO emrgContactVO){
        EmrgContact emrgContact = new EmrgContact();
        emrgContact.setId(emrgContactVO.getId());
        emrgContact.setName(emrgContactVO.getName());
        emrgContact.setEmail(emrgContactVO.getEmail());
        emrgContact.setPhone(emrgContactVO.getPhoneNumber());
        return  emrgContact;
    }
}
