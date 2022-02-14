package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表名称：u_user_info
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Mapper
public interface UserInfoAssembler {

    UserInfoAssembler INSTANCE = Mappers.getMapper(UserInfoAssembler.class);

    UserInfo mapToEntity(UserInfoCmd cmd);

    UserInfoView mapToView(UserInfo entity);

    List<UserInfoView> mapToViewList(List<UserInfo> entityList);
}
