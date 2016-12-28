package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.dao.VideoDAO;
import com.derder.business.model.City;
import com.derder.business.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
@Service
public class CityServiceImpl extends BaseDomainService implements CityService {
    @Autowired
    VideoDAO videoDAO;

    public City getCity(String cityCode){
        List<Object[]> objectArrayList = videoDAO.findCityByCode(cityCode);
        City city = new City();
        if (objectArrayList.size() > 0){
            city.setCityCode(objectArrayList.get(0)[0].toString());
            city.setCity(objectArrayList.get(0)[1].toString());
        }
        return city;
    }
}
