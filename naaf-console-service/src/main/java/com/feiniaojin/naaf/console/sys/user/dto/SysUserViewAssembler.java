package com.feiniaojin.naaf.console.sys.user.dto;

import com.feiniaojin.naaf.console.data.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity装配为view
 * 表名称：sys_user
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysUserViewAssembler {

    public SysUserView mapToView(SysUser entity) {
        SysUserView sysUserView = SysUserViewMapper.INSTANCE.mapToView(entity);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return sysUserView;
    }

    public List<SysUserView> mapToViewList(List<SysUser> entityList) {
        List<SysUserView> viewList = SysUserViewMapper.INSTANCE.mapToViewList(entityList);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return viewList;
    }

    @Mapper(componentModel = "spring")
    public interface SysUserViewMapper {
        SysUserViewMapper INSTANCE = Mappers.getMapper(SysUserViewMapper.class);

        SysUserView mapToView(SysUser entity);

        List<SysUserView> mapToViewList(List<SysUser> entityList);
    }
}
