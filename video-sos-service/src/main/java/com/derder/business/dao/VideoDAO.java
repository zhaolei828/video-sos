package com.derder.business.dao;

import com.derder.base.BaseDAO;
import com.derder.business.model.Video;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface VideoDAO extends BaseDAO<Video, Long> {

    @Query(value ="SELECT v.CITY_CODE,c.`CITY`,COUNT(DISTINCT(v.UPLOAD_USER)) userCount " +
            "FROM `video` v LEFT JOIN `city` c ON v.`CITY_CODE`=c.`CITY_CODE`  WHERE v.`UPLOAD_TIME` < CURDATE() " +
            "GROUP BY v.CITY_CODE ORDER BY userCount DESC LIMIT ?1",nativeQuery = true)
    List<Object[]> cityUserStat(int limitNum);

    @Query(value ="SELECT s.*,c.`CITY` FROM `stat_city_user_by_day` s " +
            "LEFT JOIN `city` c ON s.`CITY_CODE` = c.`CITY_CODE` " +
            "WHERE s.`CITY_CODE`=?1 ORDER BY s.`STAT_DATE` DESC LIMIT ?2,?3",nativeQuery = true)
    List<Object[]> findCityUserStatDetailList(String cityCode,int fromIndex,int limitNum);

    @Query(value ="SELECT COUNT(1) FROM `stat_city_user_by_day` WHERE `CITY_CODE`=?1",nativeQuery = true)
    int findCityUserStatDetailListCount(String cityCode);

    @Query(value ="SELECT s.*,c.`CITY` FROM `stat_city_user_by_day` s " +
            "LEFT JOIN `city` c ON s.`CITY_CODE` = c.`CITY_CODE` " +
            "WHERE s.`CITY_CODE`=?1 AND s.`STAT_DATE`=?2 LIMIT ?3,?4",nativeQuery = true)
    List<Object[]> findCityUserStatDetailListByDate(String cityCode,Date statDate,int fromIndex,int limitNum);

    @Query(value ="SELECT COUNT(1) FROM `stat_city_user_by_day` WHERE `CITY_CODE`=?1 AND `STAT_DATE`=?2",nativeQuery = true)
    int findCityUserStatDetailListCountByDate(String cityCode,Date statDate);

    @Query(value ="SELECT * FROM `CITY` WHERE `CITY_CODE`=?1",nativeQuery = true)
    List<Object[]> findCityByCode(String cityCode);
}
