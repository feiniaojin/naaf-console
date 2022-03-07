package com.feiniaojin.naaf.console.handler;

import com.feiniaojin.naaf.console.dto.UserInfoEventCmd;
import org.springframework.stereotype.Component;

/**
 * 用户信息事件调度器
 */
@Component
public class UserInfoEventDispatcher {

    void doDispatch(UserInfoEventCmd cmd) {
        cmd.getEventType();
    }
}
