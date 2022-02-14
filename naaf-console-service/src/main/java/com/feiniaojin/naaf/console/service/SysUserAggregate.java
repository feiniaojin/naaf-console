package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysUser;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
public class SysUserAggregate {

    private SysUser entity;

    private SysUserAggregate() {

    }

    private SysUserAggregate(SysUser entity) {
        this();
        this.entity = entity;
    }

    public static SysUserAggregate from(SysUser entity) {
        return new SysUserAggregate(entity);
    }

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysUser create() {
        //初始化方法
        return entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysUser update(SysUser newEntity) {

        return entity;
    }
}
