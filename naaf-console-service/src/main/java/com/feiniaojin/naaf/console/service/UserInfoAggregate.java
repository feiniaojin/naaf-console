package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.UserInfo;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
public class UserInfoAggregate {

    private UserInfo entity;

    private UserInfoAggregate() {

    }

    private UserInfoAggregate(UserInfo entity) {
        this();
        this.entity = entity;
    }

    public static UserInfoAggregate from(UserInfo entity) {
        return new UserInfoAggregate(entity);
    }

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public UserInfo create() {
        //初始化方法
        return entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public UserInfo update(UserInfo newEntity) {

        return entity;
    }
}
