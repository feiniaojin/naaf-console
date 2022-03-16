package com.feiniaojin.naaf.console.protocol.mq.listener;

import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmd;
import com.feiniaojin.naaf.console.uinfo.query.UserInfoEventQueryHandler;
import com.feiniaojin.naaf.console.uinfo.query.UserInfoService;
import com.feiniaojin.naaf.console.uinfo.query.dto.UserInfoCmd;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;

import javax.annotation.Resource;

/**
 * 请输入类描述
 *
 * @author qinyujie3
 */
@Slf4j
public class UserInfoEventListener implements MessageListener {

    Gson gson = new Gson();

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserInfoEventQueryHandler eventQueryHandler;

    @Override
    public void received(Consumer consumer, Message msg) {
        try {
            System.out.println("Message received: " + new String(msg.getData()));
            String s = new String(msg.getData());
            UserInfoCmd userInfoCmd = gson.fromJson(s, UserInfoCmd.class);

            eventQueryHandler.handle(userInfoCmd);
            consumer.acknowledge(msg);
        } catch (Exception e) {
            log.error("消费消息发生异常,msg=[{}]", gson.toJson(msg), e);
            consumer.negativeAcknowledge(msg);
        }
    }

    @Override
    public void reachedEndOfTopic(Consumer consumer) {
        MessageListener.super.reachedEndOfTopic(consumer);
    }
}
