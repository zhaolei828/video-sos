package com.derder.business.dao;

import com.derder.base.BaseDAO;
import com.derder.business.model.EmrgContact;
import com.derder.common.util.EnableFlag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
@Repository
public interface EmrgContactDAO extends BaseDAO<EmrgContact,Long> {
    List<EmrgContact> findByBandUserAndEnableFlag(long userId, EnableFlag enableFlag);

    EmrgContact findByIdAndEnableFlag(long id,EnableFlag enableFlag);
}
