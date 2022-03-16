package com.feiniaojin.naaf.console.uinfo.command.handler;

import com.feiniaojin.naaf.console.adapter.mq.publisher.PulsarPublisher;
import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@EventTypeMapping(eventType = "delete")
@Component
public class UserInfoDeleteEventHandler implements UserInfoEventHandler{

    @Resource
    private UserInfoEventRepository userInfoEventRepository;

    @Resource
    private PulsarPublisher publisher;

    Gson gson = new Gson();

    @Override
    public void handle(UserInfoEvent userInfoEvent) {
        //TODO 待操作实体是否合法，例如用户信息是否存在

        //事件入库
        userInfoEventRepository.save(userInfoEvent);
        //TODO 发送消息，暂时序列化为string再获得消息体，之后用protobuf改造
        publisher.send(gson.toJson(userInfoEvent).getBytes(StandardCharsets.UTF_8));
    }
}
