package com.derder.business.service.impl;

import com.derder.base.BaseDomainService;
import com.derder.business.dao.VideoDAO;
import com.derder.business.model.Video;
import com.derder.business.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
