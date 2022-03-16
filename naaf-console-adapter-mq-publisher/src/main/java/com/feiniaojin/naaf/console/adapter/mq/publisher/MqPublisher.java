package com.feiniaojin.naaf.console.adapter.mq.publisher;

/**
 * 消息发送接口
 */
public interface MqPublisher {
    void send(byte[] msg);
}
