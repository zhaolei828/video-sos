package com.derder.base;

import com.derder.common.Constants;
import com.derder.common.emtype.EnableFlag;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 类 编 号：
 * 类 名 称：
 * 内容摘要：
 * 创建日期：2016-12-16 14:01
 * 编码作者：zhaolei
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseModel<PK extends Serializable> implements Persistable<PK> {
    @Id
    @GeneratedValue(generator = Constants.SYSTEM_GENERATOR)
    @Column(name = "ID")
    PK ID;

    @Enumerated(EnumType.STRING)
    @Column(length=1,nullable=false,name = "ENABLE_FLAG")
    EnableFlag enableFlag=EnableFlag.Y;

    @Column(name = "CREATE_BY", updatable = false)
    Long createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", updatable = false)
    Date createTime;

    @Column(name = "UPDATE_BY")
    Long updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    Date updateTime;

    public PK getID() {
        return ID;
    }

    public void setID(PK ID) {
        this.ID = ID;
    }

    public EnableFlag getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(EnableFlag enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public PK getId() {
        return ID;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
