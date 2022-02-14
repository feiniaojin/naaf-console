package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.dto.SysResourceQuery;
import com.feiniaojin.naaf.console.dto.SysResourceView;

/**
 * sysResource类Service接口
 * 表名称：sys_resource
 * 表注释：受限资源表
 */
public interface SysResourceService {

    /**
     * 创建
     *
     * @param cmd
     */
    void create(SysResourceCmd cmd);

    /**
     * 更新
     *
     * @param cmd
     */
    void update(SysResourceCmd cmd);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    SysResourceView get(SysResourceQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageBean<SysResourceView> pageList(SysResourceQuery query);
}
