package com.feiniaojin.naaf.console.evs.handler;

import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EventTypeMapping(eventType = "create")
@Component
public class UserInfoCreateEventHandler implements UserInfoEventHandler {

    @Resource
    private UserInfoEventRepository userInfoEventRepository;

    @Override
    public void handle(UserInfoEvent userInfoEvent) {
        //TODO 唯一性校验，是否接受事件

        //事件入库
        userInfoEventRepository.save(userInfoEvent);
        //TODO 异步发送消息

    }
}
