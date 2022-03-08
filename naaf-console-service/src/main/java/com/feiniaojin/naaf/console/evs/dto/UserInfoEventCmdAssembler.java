package com.feiniaojin.naaf.console.evs.dto;

import com.feiniaojin.naaf.console.entity.UserInfoEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * CMD装配模型
 * 表名称：u_user_info_event
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class UserInfoEventCmdAssembler {

    public UserInfoEvent mapToEntity(UserInfoEventCmd cmd) {
        UserInfoEvent userInfoEvent = UserInfoEventCmdMapper.INSTANCE.mapToEntity(cmd);
        //TODO 完成装配逻辑，作为mapstruct的补充，避免将逻辑泄露到service中
        return userInfoEvent;
    }

    @Mapper(componentModel = "spring")
    public interface UserInfoEventCmdMapper {
        UserInfoEventCmdMapper INSTANCE = Mappers.getMapper(UserInfoEventCmdMapper.class);

        UserInfoEvent mapToEntity(UserInfoEventCmd cmd);
    }
}
