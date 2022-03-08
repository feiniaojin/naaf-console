package com.feiniaojin.naaf.console.adapter.mq.publisher;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqPublisherConfig {

    /**
     * 默认提供的消息发送者
     *
     * @return
     */
    @ConditionalOnMissingBean(value = {MqPublisher.class})
    public MqPublisher defaultMqPublisher() {
        return new DefaultMqPublisher();
    }
}
