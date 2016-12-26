package com.derder.business.dao;

import com.derder.base.BaseDAO;
import com.derder.business.model.Video;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface VideoDAO extends BaseDAO<Video, Long> {

    @Query(value ="SELECT v.CITY_CODE,c.`CITY`,COUNT(v.UPLOAD_USER) userCount " +
            "FROM `video` v LEFT JOIN `city` c ON v.`CITY_CODE`=c.`CITY_CODE` " +
            "GROUP BY v.CITY_CODE ORDER BY userCount DESC LIMIT ?1",nativeQuery = true)
    List<Object[]> cityUserStat(int limitNum);
}
