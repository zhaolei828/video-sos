package com.derder.business.model;

import com.derder.base.BaseModel;
import com.derder.common.Constants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/18.
 */
@Entity
@Table(name = "emrg_contact")
@GenericGenerator(name = Constants.SYSTEM_GENERATOR, strategy = Constants.ASSIGNED)
public class EmrgContact extends BaseModel<Long> {
    @Column(name = "EMRG_CONTACT_NAME")
    String name;
    @Column(name = "ENRG_CONTACT_EMAIL")
    String email;
    @Column(name = "EMRG_CONTACT_PHONE")
    String phone;
    @Column(name = "BAND_USER")
    Long bandUser;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getBandUser() {
        return bandUser;
    }

    public void setBandUser(Long bandUser) {
        this.bandUser = bandUser;
    }
}
