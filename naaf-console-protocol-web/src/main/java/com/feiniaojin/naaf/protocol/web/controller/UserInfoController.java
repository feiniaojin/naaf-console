package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.dto.UserInfoQuery;
import com.feiniaojin.naaf.console.dto.UserInfoView;
import com.feiniaojin.naaf.console.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserInfo类Controller类
 * 表名称：u_user_info
 * 表注释：用户信息表
 */
@RestController
@RequestMapping("/uinfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/create")
    public void create(UserInfoCmd command){
        userInfoService.create(command);
    }

    @RequestMapping("/update")
    public void update(UserInfoCmd command){
        userInfoService.update(command);
    }

    @RequestMapping("/detail")
    public UserInfoView detail(UserInfoQuery query){
        return userInfoService.get(query);
    }

    @RequestMapping("/pageList")
    public PageBean<UserInfoView> pageList(UserInfoQuery query){
        return userInfoService.pageList(query);
    }
}
