package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.console.sys.resource.dto.SysResourceCmd;

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

}
