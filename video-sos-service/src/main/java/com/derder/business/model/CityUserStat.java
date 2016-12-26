package com.derder.business.model;

/**
 * Created by Administrator on 2016/12/26.
 */
public class CityUserStat {
    private String cityCode;
    private String city;
    private  String count;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
