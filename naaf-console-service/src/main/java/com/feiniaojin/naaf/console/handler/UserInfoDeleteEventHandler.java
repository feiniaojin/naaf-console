package com.feiniaojin.naaf.console.handler;

import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import org.springframework.stereotype.Component;

@EventTypeMapping(eventType = "delete")
@Component
public class UserInfoDeleteEventHandler implements UserInfoEventHandler<UserInfoEvent>{
    @Override
    public void handle(UserInfoEvent userInfoEvent) {

    }
}
