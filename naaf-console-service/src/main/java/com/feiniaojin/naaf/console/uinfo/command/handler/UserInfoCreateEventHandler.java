package com.feiniaojin.naaf.console.uinfo.command.handler;

import com.feiniaojin.naaf.console.adapter.mq.publisher.PulsarPublisher;
import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.integration.id.IdGeneratorIntegration;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@EventTypeMapping(eventType = "create")
@Component
public class UserInfoCreateEventHandler implements UserInfoEventHandler {

    @Resource
    private UserInfoEventRepository userInfoEventRepository;

    @Resource
    private IdGeneratorIntegration idGeneratorIntegration;

    @Resource
    private PulsarPublisher publisher;

    Gson gson = new Gson();

    @Override
    public void handle(UserInfoEvent userInfoEvent) {
        //TODO 唯一性校验，是否接受事件
        userInfoEvent.setUid(String.valueOf(idGeneratorIntegration.getUid()));
        //事件入库
        userInfoEventRepository.save(userInfoEvent);
        //TODO 发送消息，暂时序列化为string再获得消息体，之后用protobuf改造
        publisher.send(gson.toJson(userInfoEvent).getBytes(StandardCharsets.UTF_8));
    }
}
