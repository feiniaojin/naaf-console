package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity装配为view
 * 表名称：sys_role
 * 表注释：角色表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysRoleViewAssembler {

    public SysRoleView mapToView(SysRole entity) {
        SysRoleView sysRoleView = SysRoleViewMapper.INSTANCE.mapToView(entity);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return sysRoleView;
    }

    public List<SysRoleView> mapToViewList(List<SysRole> entityList) {
        List<SysRoleView> viewList = SysRoleViewMapper.INSTANCE.mapToViewList(entityList);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return viewList;
    }

    @Mapper
    public interface SysRoleViewMapper {
        SysRoleViewMapper INSTANCE = Mappers.getMapper(SysRoleViewMapper.class);

        SysRoleView mapToView(SysRole entity);

        List<SysRoleView> mapToViewList(List<SysRole> entityList);
    }
}
