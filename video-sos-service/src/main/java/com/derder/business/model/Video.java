package com.derder.business.model;

import com.derder.base.BaseModel;
import com.derder.common.Constants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/26.
 */
@Entity
@Table(name = "video")
@GenericGenerator(name = Constants.SYSTEM_GENERATOR, strategy = Constants.ASSIGNED)
public class Video extends BaseModel<Long> {
    private String videoName;
    private String videoPath;
    private Date uploadTime;
    private long uploadUser;
    private int provice;
    private String cityCode;
    private int district;
    private String uploadAddressJson;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(long uploadUser) {
        this.uploadUser = uploadUser;
    }

    public int getProvice() {
        return provice;
    }

    public void setProvice(int provice) {
        this.provice = provice;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getUploadAddressJson() {
        return uploadAddressJson;
    }

    public void setUploadAddressJson(String uploadAddressJson) {
        this.uploadAddressJson = uploadAddressJson;
    }
}
