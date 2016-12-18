package com.derder.business.emtype;

/**
 * Created by Administrator on 2016/12/18.
 */
public enum UserGroup {
    COMMON_USER(1),SYSTEM_MANAGER(0);
    int groupId;

    UserGroup(int groupId) {
        this.groupId = groupId;
    }
}
