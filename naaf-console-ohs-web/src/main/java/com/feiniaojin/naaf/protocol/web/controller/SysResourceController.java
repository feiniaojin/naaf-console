package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.console.sys.resource.SysResourceService;
import com.feiniaojin.naaf.console.sys.resource.dto.SysResourceCmd;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SysResource类Controller类
 * 表名称：sys_resource
 * 表注释：受限资源表
 */
@RestController
@RequestMapping("/sysResource")
public class SysResourceController {
    @Resource
    private SysResourceService sysResourceService;

    @RequestMapping("/create")
    public void create(SysResourceCmd command) {
        sysResourceService.create(command);
    }

    @RequestMapping("/update")
    public void update(SysResourceCmd command) {
    }

}
