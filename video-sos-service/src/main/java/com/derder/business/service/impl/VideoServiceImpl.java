package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.dao.VideoDAO;
import com.derder.business.model.CityUserStat;
import com.derder.business.model.CityUserStatDetail;
import com.derder.business.model.Video;
import com.derder.business.service.VideoService;
import com.derder.common.util.DateUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public Page<CityUserStatDetail> getCityUserStatDetailList(String cityCode, Date statDate, int pageNo, int pageSize) {
        List<CityUserStatDetail> list = Lists.newArrayList();
        int count;
        int fromIndex = (pageNo-1)*pageSize;
        List<Object[]> objectArrayList;
        if (null == statDate){
            objectArrayList = videoDAO.findCityUserStatDetailList(cityCode,fromIndex,pageSize);
            count = videoDAO.findCityUserStatDetailListCount(cityCode);
        }else {
            objectArrayList = videoDAO.findCityUserStatDetailListByDate(cityCode,statDate,fromIndex,pageSize);
            count = videoDAO.findCityUserStatDetailListCountByDate(cityCode,statDate);
        }

        if (null == objectArrayList) {
            objectArrayList = Lists.newArrayList();
        }

        for (Object[] objectArray : objectArrayList) {
            CityUserStatDetail cityUserStatDetail = new CityUserStatDetail();
            cityUserStatDetail.setCityCode(objectArray[1].toString());
            cityUserStatDetail.setCity(objectArray[objectArray.length-1].toString());
            cityUserStatDetail.setStatDate(DateUtil.string2Date(objectArray[2].toString()));
            cityUserStatDetail.setCountDay(Integer.valueOf(objectArray[3].toString()));
            cityUserStatDetail.setCountWeek(Integer.valueOf(objectArray[4].toString()));
            cityUserStatDetail.setCountMonth(Integer.valueOf(objectArray[5].toString()));
            cityUserStatDetail.setCountHour1(Integer.valueOf(objectArray[6].toString()));
            cityUserStatDetail.setCountHour2(Integer.valueOf(objectArray[7].toString()));
            cityUserStatDetail.setCountHour3(Integer.valueOf(objectArray[8].toString()));
            cityUserStatDetail.setCountHour4(Integer.valueOf(objectArray[9].toString()));
            cityUserStatDetail.setCountHour5(Integer.valueOf(objectArray[10].toString()));
            cityUserStatDetail.setCountHour6(Integer.valueOf(objectArray[11].toString()));
            cityUserStatDetail.setCountHour7(Integer.valueOf(objectArray[12].toString()));
            cityUserStatDetail.setCountHour8(Integer.valueOf(objectArray[13].toString()));
            cityUserStatDetail.setCountHour9(Integer.valueOf(objectArray[14].toString()));
            cityUserStatDetail.setCountHour10(Integer.valueOf(objectArray[15].toString()));
            cityUserStatDetail.setCountHour11(Integer.valueOf(objectArray[16].toString()));
            cityUserStatDetail.setCountHour12(Integer.valueOf(objectArray[17].toString()));
            cityUserStatDetail.setCountHour13(Integer.valueOf(objectArray[18].toString()));
            cityUserStatDetail.setCountHour14(Integer.valueOf(objectArray[19].toString()));
            cityUserStatDetail.setCountHour15(Integer.valueOf(objectArray[20].toString()));
            cityUserStatDetail.setCountHour16(Integer.valueOf(objectArray[21].toString()));
            cityUserStatDetail.setCountHour17(Integer.valueOf(objectArray[22].toString()));
            cityUserStatDetail.setCountHour18(Integer.valueOf(objectArray[23].toString()));
            cityUserStatDetail.setCountHour19(Integer.valueOf(objectArray[24].toString()));
            cityUserStatDetail.setCountHour20(Integer.valueOf(objectArray[25].toString()));
            cityUserStatDetail.setCountHour21(Integer.valueOf(objectArray[26].toString()));
            cityUserStatDetail.setCountHour22(Integer.valueOf(objectArray[27].toString()));
            cityUserStatDetail.setCountHour23(Integer.valueOf(objectArray[28].toString()));
            cityUserStatDetail.setCountHour24(Integer.valueOf(objectArray[29].toString()));
            list.add(cityUserStatDetail);
        }

        Page<CityUserStatDetail> page = new Page<CityUserStatDetail>() {
            @Override
            public int getTotalPages() {
                if (count != 0 && count % pageSize ==0){
                    return count / pageSize;
                }else {
                    return count / pageSize +1;
                }
            }

            @Override
            public long getTotalElements() {
                return count;
            }

            @Override
            public <S> Page<S> map(Converter<? super CityUserStatDetail, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageNo;
            }

            @Override
            public int getSize() {
                return pageSize;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<CityUserStatDetail> getContent() {
                return list;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                if (pageNo == 1) return true;
                else return false;
            }

            @Override
            public boolean isLast() {
                if (getNumber() == getTotalPages()) return true;
                else return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<CityUserStatDetail> iterator() {
                return null;
            }
        };
        return page;
    }
}
