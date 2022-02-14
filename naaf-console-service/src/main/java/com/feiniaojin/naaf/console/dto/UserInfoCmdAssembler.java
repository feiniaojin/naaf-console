package com.feiniaojin.naaf.console.dto;

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
        //TODO 完成装配逻辑，作为mapstruct的补充，避免将逻辑泄露到service中
        return userInfo;
    }

    @Mapper
    public interface UserInfoCmdMapper {
        UserInfoCmdMapper INSTANCE = Mappers.getMapper(UserInfoCmdMapper.class);

        UserInfo mapToEntity(UserInfoCmd cmd);
    }
}
