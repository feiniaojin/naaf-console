package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * CMD装配模型
 * 表名称：sys_role
 * 表注释：角色表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysRoleCmdAssembler {

    public SysRole mapToEntity(SysRoleCmd cmd) {
        SysRole sysRole = SysRoleCmdMapper.INSTANCE.mapToEntity(cmd);
        //TODO 完成装配逻辑，作为mapstruct的补充，避免将逻辑泄露到service中
        return sysRole;
    }

    @Mapper
    public interface SysRoleCmdMapper {
        SysRoleCmdMapper INSTANCE = Mappers.getMapper(SysRoleCmdMapper.class);

        SysRole mapToEntity(SysRoleCmd cmd);
    }
}
