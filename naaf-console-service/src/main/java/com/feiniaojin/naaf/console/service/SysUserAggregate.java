package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysUserAggregate {

    /**
     * 用户实体
     */
    private SysUser entity;

    /**
     * 用户拥有的角色
     */
    private List<SysRoleAggregate> roleAggregates;

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
        //更新的具体逻辑
        return entity;
    }
}
