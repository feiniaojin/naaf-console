package com.feiniaojin.naaf.console.protocol.mq.listener;

import com.feiniaojin.naaf.console.uinfo.command.UserInfoEventService;
import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmd;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;

import javax.annotation.Resource;

/**
 * 账号时间listener
 *
 * @author qinyujie3
 */
@Slf4j
public class SysUserEventListener implements MessageListener {

    @Resource
    private UserInfoEventService userInfoEventService;

    Gson gson = new Gson();

    @Override
    public void received(Consumer consumer, Message msg) {
        try {
            log.info("sys_user Message received: {}", new String(msg.getData()));
            String s = new String(msg.getData());
            UserInfoEventCmd userInfoEventCmd = gson.fromJson(s, UserInfoEventCmd.class);
            userInfoEventService.handleEvent(userInfoEventCmd);
            consumer.acknowledge(msg);
        } catch (Exception e) {
            log.error("消费消息发生异常,msg=[{}]", gson.toJson(new String(msg.getData())), e);
            consumer.negativeAcknowledge(msg);
        }
    }
}
