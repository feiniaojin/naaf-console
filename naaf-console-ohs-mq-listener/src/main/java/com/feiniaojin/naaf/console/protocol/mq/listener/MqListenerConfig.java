package com.feiniaojin.naaf.console.protocol.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqListenerConfig {

    @Value("${pulsar.service.url}")
    private String url;

    @Value("${pulsar.topic.evs_user_info_event}")
    private String topicEvsUserInfo;

    /**
     * 默认提供的消息发送者
     *
     * @return
     */
    @Bean
    public Consumer pulsarConsumer(MessageListener messageListener) {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();
            Consumer consumer = client.newConsumer()
                    .topic(topicEvsUserInfo)
                    .subscriptionName("my-subscription")
                    .messageListener(messageListener)
                    .subscribe();
            return consumer;
        } catch (Exception e) {
            log.error("初始化pulsar失败", e);
            throw new RuntimeException("");
        }
    }

    @Bean
    public MessageListener messageListener() {
        return new UserInfoEventListener();
    }
}
