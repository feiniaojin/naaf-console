package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserCmd;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserQuery;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserView;

/**
 * sysUser类Service接口
 * 表名称：sys_user
 * 表注释：用户账号表
 */
public interface SysUserService {

    /**
     * 创建
     *
     * @param cmd
     */
    void create(SysUserCmd cmd);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    SysUserView get(SysUserQuery query);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageBean<SysUserView> pageList(SysUserQuery query);


    /**
     * 分配角色
     *
     * @param cmd
     */
    void assignRoles(SysUserCmd cmd);
}
