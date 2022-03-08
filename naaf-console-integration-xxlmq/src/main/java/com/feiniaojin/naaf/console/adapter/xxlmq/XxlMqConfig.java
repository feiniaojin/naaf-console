package com.feiniaojin.naaf.console.adapter.xxlmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xxl.mq.admin")
@MapperScan(basePackages = {
        "com.xxl.mq.admin.dao"
})
public class XxlMqConfig {
}
