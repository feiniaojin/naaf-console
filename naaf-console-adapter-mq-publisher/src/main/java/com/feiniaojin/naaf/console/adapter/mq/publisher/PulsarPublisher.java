package com.feiniaojin.naaf.console.adapter.mq.publisher;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;

/**
 * 默认mq发送者，基于pulsar实现
 */
@Slf4j
public class PulsarPublisher implements MqPublisher {

    private Producer<byte[]> produce;

    public PulsarPublisher(Producer<byte[]> produce) {
        this.produce = produce;
    }

    @Override
    public void send(byte[] msg) {
        try {
            produce.send(msg);
        } catch (Exception e) {
            log.error("PulsarPublisher:发送失败", e);
            throw new RuntimeException("PulsarPublisher:发送失败");
        }
    }
}
