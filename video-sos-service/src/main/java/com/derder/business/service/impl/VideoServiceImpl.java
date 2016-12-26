package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.dao.VideoDAO;
import com.derder.business.model.CityUserStat;
import com.derder.business.model.Video;
import com.derder.business.service.VideoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2016/12/26.
 */
@Service
public class VideoServiceImpl extends BaseDomainService implements VideoService {
    @Autowired
    VideoDAO videoDAO;

    @Override
    public Video addVideo(Video video) {
        Long id = generateID();
        video.setId(id);
        return videoDAO.save(video);
    }

    @Override
    public List<CityUserStat> cityUserStatList(Integer limitNum) {
        if (null == limitNum || limitNum <= 0){
            limitNum = commonLimitNum;
        }
        List<Object[]> objectArrayList = videoDAO.cityUserStat(limitNum);
        List<CityUserStat> list = Lists.newArrayList();
        for (Object[] objectArray:objectArrayList
             ) {
            CityUserStat cityUserStat = new CityUserStat();
            if ( null != objectArray[0]) {
                cityUserStat.setCityCode(objectArray[0].toString());
            }else {
                cityUserStat.setCityCode("");
            }

            if ( null != objectArray[1]) {
                cityUserStat.setCity(objectArray[1].toString());
            }else {
                cityUserStat.setCity("");
            }

            if ( null != objectArray[2]) {
                cityUserStat.setCount(objectArray[2].toString());
            }else {
                cityUserStat.setCount("0");
            }
            list.add(cityUserStat);
        }
        return list;
    }
}
