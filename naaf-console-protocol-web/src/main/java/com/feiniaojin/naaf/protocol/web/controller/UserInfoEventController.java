package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmd;
import com.feiniaojin.naaf.console.uinfo.command.UserInfoEventService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserInfoEvent类Controller类
 * 表名称：u_user_info_event
 * 表注释：用户信息表事件
 */
@RestController
@RequestMapping("/userInfoEvent")
public class UserInfoEventController {
    @Resource
    private UserInfoEventService userInfoEventService;

    @RequestMapping("/create")
    public void create(@RequestBody UserInfoEventCmd command) {
        userInfoEventService.handleEvent(command);
    }
}
