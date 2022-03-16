package com.feiniaojin.naaf.console.uinfo.command;

import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmd;
import com.feiniaojin.naaf.console.uinfo.command.dto.UserInfoEventCmdAssembler;
import com.feiniaojin.naaf.console.uinfo.command.handler.UserInfoEventDispatcher;
import com.feiniaojin.naaf.console.uinfo.command.handler.UserInfoEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserInfoEvent类Service实现类
 * 表名称：u_user_info_event
 * 表注释：用户信息表
 */
@Service
@Slf4j
public class UserInfoEventServiceImpl implements UserInfoEventService {

    @Resource
    private UserInfoEventCmdAssembler cmdAssembler;

    @Resource
    private UserInfoEventDispatcher dispatcher;

    /**
     * 处理事件，消息由具体的handler自己控制发送逻辑
     *
     * @param cmd
     */
    @Override
    public void handleEvent(UserInfoEventCmd cmd) {
        UserInfoEvent userInfoEvent = cmdAssembler.mapToEntity(cmd);
        UserInfoEventHandler eventHandler = dispatcher.doDispatch(cmd.getEventType());
        eventHandler.handle(userInfoEvent);
    }
}
