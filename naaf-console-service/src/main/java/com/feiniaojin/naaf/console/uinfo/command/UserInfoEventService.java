package com.feiniaojin.naaf.console.uinfo.command;

import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmd;

/**
 * userInfoEvent类Service接口
 * 表名称：u_user_info_event
 * 表注释：用户信息表
 */
public interface UserInfoEventService {

    /**
     * 创建
     *
     * @param cmd
     */
    void handleEvent(UserInfoEventCmd cmd);
}
