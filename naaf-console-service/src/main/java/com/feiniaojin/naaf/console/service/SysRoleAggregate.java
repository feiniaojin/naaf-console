package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysRole;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
public class SysRoleAggregate {

    private SysRole entity;

    private SysRoleAggregate() {

    }

    private SysRoleAggregate(SysRole entity) {
        this();
        this.entity = entity;
    }

    public static SysRoleAggregate from(SysRole entity) {
        return new SysRoleAggregate(entity);
    }

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysRole create() {
        //初始化方法
        return entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysRole update(SysRole newEntity) {

        return entity;
    }
}
