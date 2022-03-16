package com.feiniaojin.naaf.console.uinfo.query.dto;

import com.feiniaojin.naaf.console.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * CMD装配模型
 * 表名称：u_user_info
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class UserInfoCmdAssembler {

    public UserInfo mapToEntity(UserInfoCmd cmd) {
        UserInfo userInfo = UserInfoCmdMapper.INSTANCE.mapToEntity(cmd);
        userInfo.setEvsEventId(cmd.getEventId());
        return userInfo;
    }

    @Mapper(componentModel = "spring")
    public interface UserInfoCmdMapper {
        UserInfoCmdMapper INSTANCE = Mappers.getMapper(UserInfoCmdMapper.class);

        UserInfo mapToEntity(UserInfoCmd cmd);
    }
}
