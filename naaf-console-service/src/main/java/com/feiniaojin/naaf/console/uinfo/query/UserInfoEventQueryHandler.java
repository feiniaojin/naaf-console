package com.feiniaojin.naaf.console.uinfo.query;

import com.feiniaojin.naaf.console.uinfo.query.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.uinfo.query.dto.UserInfoCmdAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户信息事件分发器
 */
@Component
@Slf4j
public class UserInfoEventQueryHandler {

    @Resource
    private UserInfoService userInfoService;

    public void handle(UserInfoCmd userInfoCmd) {

        String eventType = userInfoCmd.getEventType();
        if ("create".equals(eventType)) {
            userInfoService.create(userInfoCmd);
        } else if ("update".equals(eventType)) {
            userInfoService.update(userInfoCmd);
        }
    }
}
