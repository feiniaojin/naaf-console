package com.feiniaojin.naaf.console.evs.handler;

import com.feiniaojin.naaf.console.commons.EventTypeMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户信息事件分发器
 */
@Component
@Slf4j
public class UserInfoEventDispatcher implements ApplicationContextAware {


    private Map<String, UserInfoEventHandler> map = new ConcurrentHashMap<>();

    public UserInfoEventHandler doDispatch(String eventType) {
        UserInfoEventHandler handler = map.get(eventType);
        if (handler == null) {
            log.error("用户信息事件分发异常:找不到对应的handler:eventType=[{}]", eventType);
            throw new RuntimeException("用户信息事件分发异常:找不到对应的handler,eventType=" + eventType);
        }
        return handler;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, UserInfoEventHandler> beansOfType = applicationContext.getBeansOfType(UserInfoEventHandler.class);
        for (Map.Entry<String, UserInfoEventHandler> entry : beansOfType.entrySet()) {
            UserInfoEventHandler eventHandler = entry.getValue();
            EventTypeMapping eventTypeMapping = eventHandler.getClass().getAnnotation(EventTypeMapping.class);
            map.put(eventTypeMapping.eventType(), eventHandler);
        }
    }
}
