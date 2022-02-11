package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.ResourceCmd;
import com.feiniaojin.naaf.console.dto.ResourceQuery;
import com.feiniaojin.naaf.console.dto.ResourceView;

/**
 * resource类Service接口
 * 表名称：t_resource
 * 表注释：受限资源表
 */
public interface ResourceService {

    /**
     * 创建
     *
     * @param command
     */
    void create(ResourceCmd command);

    /**
     * 更新
     *
     * @param command
     */
    void update(ResourceCmd command);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    ResourceView get(ResourceQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageBean<ResourceView> pageList(ResourceQuery query);
}
