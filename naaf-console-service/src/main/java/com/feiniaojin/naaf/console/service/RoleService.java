package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.RoleCmd;
import com.feiniaojin.naaf.console.dto.RoleQuery;
import com.feiniaojin.naaf.console.dto.RoleView;

/**
 * role类Service接口
 * 表名称：t_role
 * 表注释：角色表
 */
public interface RoleService {

    /**
     * 创建
     *
     * @param command
     */
    void create(RoleCmd command);

    /**
     * 更新
     *
     * @param command
     */
    void update(RoleCmd command);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    RoleView get(RoleQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageBean<RoleView> pageList(RoleQuery query);
}
