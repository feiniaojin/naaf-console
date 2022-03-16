package com.feiniaojin.naaf.console.protocol.mq.listener;

import com.feiniaojin.naaf.console.adapter.mq.publisher.PulsarPublisher;
import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MqListenerConfig {


    @Value("${pulsar.service.url}")
    private String url;

    @Value("${pulsar.topic.user_info_event}")
    private String topic;

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
                    .topic("my-topic")
                    .subscriptionName("my-subscription")
                    .messageListener(messageListener)
                    .subscribe();
            return consumer;
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    @Bean
    public MessageListener messageListener() {
        return new UserInfoEventListener();
    }
}
