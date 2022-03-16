package com.feiniaojin.naaf.console.uinfo.command.dto;

import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import com.feiniaojin.naaf.console.integration.id.IdGeneratorIntegration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CMD装配模型
 * 表名称：u_user_info_event
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class UserInfoEventCmdAssembler {

    @Resource
    public IdGeneratorIntegration idGeneratorIntegration;

    public UserInfoEvent mapToEntity(UserInfoEventCmd cmd) {
        UserInfoEvent userInfoEvent = UserInfoEventCmdMapper.INSTANCE.mapToEntity(cmd);
        userInfoEvent.setEventId(String.valueOf(idGeneratorIntegration.getUid()));
        return userInfoEvent;
    }

    @Mapper(componentModel = "spring")
    public interface UserInfoEventCmdMapper {
        UserInfoEventCmdMapper INSTANCE = Mappers.getMapper(UserInfoEventCmdMapper.class);

        UserInfoEvent mapToEntity(UserInfoEventCmd cmd);
    }
}
