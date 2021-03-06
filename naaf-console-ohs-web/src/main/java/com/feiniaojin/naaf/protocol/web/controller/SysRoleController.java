package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleQuery;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleView;
import com.feiniaojin.naaf.console.sys.role.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SysRole类Controller类
 * 表名称：sys_role
 * 表注释：角色表
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/create")
    public void create(SysRoleCmd command){
        sysRoleService.create(command);
    }

    @RequestMapping("/detail")
    public SysRoleView detail(SysRoleQuery query){
        return sysRoleService.get(query);
    }

    @RequestMapping("/pageList")
    public PageBean<SysRoleView> pageList(SysRoleQuery query){
        return sysRoleService.pageList(query);
    }
}
