package com.feiniaojin.naaf.console.uinfo.command.handler;

import com.feiniaojin.naaf.console.adapter.mq.publisher.PulsarPublisher;
import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.integration.id.IdGeneratorIntegration;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import com.feiniaojin.naaf.console.uinfo.command.events.CqrsEvent;
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

    @Resource(name = "evsUserInfoPublisher")
    private PulsarPublisher evsUserInfoPublisher;

    Gson gson = new Gson();

    @Override
    public void handle(UserInfoEvent userInfoEvent) {

        //赋值事件id
        userInfoEvent.setEventId(idGeneratorIntegration.getStringUid());

        //事件入库
        userInfoEventRepository.save(userInfoEvent);

        //发送事件通知Query侧
        CqrsEvent event = CqrsEvent.builder()
                .uid(userInfoEvent.getUid())
                .mobilePhone(userInfoEvent.getMobilePhone())
                .eventId(userInfoEvent.getEventId())
                .eventType("create")
                .userName(userInfoEvent.getUserName())
                .email(userInfoEvent.getEmail())
                .build();
        evsUserInfoPublisher.send(gson.toJson(event).getBytes(StandardCharsets.UTF_8));
    }
}
