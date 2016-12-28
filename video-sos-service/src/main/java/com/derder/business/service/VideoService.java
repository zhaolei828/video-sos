package com.derder.business.service;

import com.derder.business.model.CityUserStat;
import com.derder.business.model.CityUserStatDetail;
import com.derder.business.model.Video;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface VideoService {
    Video addVideo(Video video);

    List<CityUserStat> cityUserStatList(Integer limitNum);

    List<CityUserStatDetail> getCityUserStatDetailList(String cityCode, Date statDate,int fromIndex,int limitNum);
}
