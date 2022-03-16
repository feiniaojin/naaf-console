package com.feiniaojin.naaf.console.uinfo.query.dto;

import com.feiniaojin.naaf.console.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity装配为view
 * 表名称：u_user_info
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class UserInfoViewAssembler {

    public UserInfoView mapToView(UserInfo entity) {
        UserInfoView userInfoView = UserInfoViewMapper.INSTANCE.mapToView(entity);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return userInfoView;
    }

    public List<UserInfoView> mapToViewList(List<UserInfo> entityList) {
        List<UserInfoView> viewList = UserInfoViewMapper.INSTANCE.mapToViewList(entityList);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return viewList;
    }

    @Mapper(componentModel = "spring")
    public interface UserInfoViewMapper {
        UserInfoViewMapper INSTANCE = Mappers.getMapper(UserInfoViewMapper.class);

        UserInfoView mapToView(UserInfo entity);

        List<UserInfoView> mapToViewList(List<UserInfo> entityList);
    }
}
