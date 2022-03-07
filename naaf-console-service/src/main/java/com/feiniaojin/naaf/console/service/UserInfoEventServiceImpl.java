package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.UserInfoEventCmd;
import com.feiniaojin.naaf.console.dto.UserInfoEventCmdAssembler;
import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.repository.UserInfoEventRepository;
import com.google.gson.Gson;
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
    private UserInfoEventRepository userInfoEventRepository;

    @Resource
    private UserInfoEventCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    private Gson gson = new Gson();

    /**
     * 不断插入事件，并发出消息
     *
     * @param cmd
     */
    @Override
    public void handleEvent(UserInfoEventCmd cmd) {
        //根据cmd组装实体
        UserInfoEvent userInfoEvent = cmdAssembler.mapToEntity(cmd);
        //TODO:唯一性校验

        if ("create".equalsIgnoreCase(cmd.getEventType())) {
            userInfoEvent.setUid(String.valueOf(idGeneratorAdapter.getUid()));
        }
        log.info("UserInfoEvent create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(userInfoEvent));
        userInfoEventRepository.save(userInfoEvent);
        //TODO:发布事件
    }
}
