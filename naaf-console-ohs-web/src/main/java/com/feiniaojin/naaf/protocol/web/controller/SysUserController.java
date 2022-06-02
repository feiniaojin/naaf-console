package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserCmd;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserQuery;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserView;
import com.feiniaojin.naaf.console.sys.user.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SysUser类Controller类
 * 表名称：sys_user
 * 表注释：用户信息表
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/create")
    public void create(SysUserCmd command){
        sysUserService.create(command);
    }

    @RequestMapping("/detail")
    public SysUserView detail(SysUserQuery query){
        return sysUserService.get(query);
    }

    @RequestMapping("/pageList")
    public PageBean<SysUserView> pageList(SysUserQuery query){
        return sysUserService.pageList(query);
    }
}
