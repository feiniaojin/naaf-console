package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleQuery;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleView;

/**
 * sysRole类Service接口
 * 表名称：sys_role
 * 表注释：角色表
 */
public interface SysRoleService {

    /**
     * 创建
     *
     * @param cmd
     */
    void create(SysRoleCmd cmd);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    SysRoleView get(SysRoleQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageBean<SysRoleView> pageList(SysRoleQuery query);
}
