package com.feiniaojin.naaf.console.evs.handler;

import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EventTypeMapping(eventType = "delete")
@Component
public class UserInfoDeleteEventHandler implements UserInfoEventHandler{

    @Resource
    private UserInfoEventRepository userInfoEventRepository;

    @Override
    public void handle(UserInfoEvent userInfoEvent) {
        //TODO 待操作实体是否合法，例如用户信息是否存在

        //事件入库
        userInfoEventRepository.save(userInfoEvent);
        //TODO 异步发送消息
    }
}
