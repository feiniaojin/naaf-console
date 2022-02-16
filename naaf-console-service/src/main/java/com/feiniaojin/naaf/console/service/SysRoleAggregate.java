package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysRole;
import com.feiniaojin.naaf.console.entity.SysRoleRelResource;
import lombok.Data;

import java.util.List;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysRoleAggregate {

    private SysRole entity;

    private List<String> resourceIdList;

    /**
     * 角色关联的受限资源
     */
    private List<SysRoleRelResource> roleRelResourceList;

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
    public SysRole update(SysRole newEntity, List<String> resourceIdLis) {
        //更新的具体逻辑
        return entity;
    }
}
