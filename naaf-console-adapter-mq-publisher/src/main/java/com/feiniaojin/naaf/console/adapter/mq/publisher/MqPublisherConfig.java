package com.feiniaojin.naaf.console.adapter.mq.publisher;

import org.apache.pulsar.client.api.BatcherBuilder;
import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MqPublisherConfig {

    @Value("${pulsar.service.url}")
    private String url;

    @Value("${pulsar.topic.evs_user_info_event}")
    private String topicEvsUserInfo;

    @Value("${pulsar.topic.sys_user_event}")
    private String topicSysUser;

    /**
     * 默认提供的消息发送者
     *
     * @return
     */
    @Bean
    public PulsarPublisher evsUserInfoPublisher() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();
            Producer<byte[]> producer = client.newProducer()
                    .topic(topicEvsUserInfo)
                    .compressionType(CompressionType.LZ4)
                    .sendTimeout(0, TimeUnit.SECONDS)
                    .enableBatching(true)
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .batchingMaxMessages(1000)
                    .maxPendingMessages(1000)
                    .blockIfQueueFull(true)
                    .roundRobinRouterBatchingPartitionSwitchFrequency(10)
                    .batcherBuilder(BatcherBuilder.DEFAULT)
                    .create();
            return new PulsarPublisher(producer);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    /**
     * u_user_info表发布事件
     *
     * @return
     */
    @Bean
    public PulsarPublisher sysUserPublisher() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();
            Producer<byte[]> producer = client.newProducer()
                    .topic(topicSysUser)
                    .compressionType(CompressionType.LZ4)
                    .sendTimeout(0, TimeUnit.SECONDS)
                    .enableBatching(true)
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .batchingMaxMessages(1000)
                    .maxPendingMessages(1000)
                    .blockIfQueueFull(true)
                    .roundRobinRouterBatchingPartitionSwitchFrequency(10)
                    .batcherBuilder(BatcherBuilder.DEFAULT)
                    .create();
            return new PulsarPublisher(producer);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }
}
