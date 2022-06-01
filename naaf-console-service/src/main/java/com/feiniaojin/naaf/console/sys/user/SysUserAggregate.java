package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.console.data.SysUser;
import com.feiniaojin.naaf.console.data.SysUserRelRole;
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
    private List<String> roleIdList;

    /**
     * 用户拥有的角色
     */
    private List<SysUserRelRole> userRelRoleList;

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysUser create() {
        return this.entity;
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

    public void assignRoles(SysUser input) {

    }
}
