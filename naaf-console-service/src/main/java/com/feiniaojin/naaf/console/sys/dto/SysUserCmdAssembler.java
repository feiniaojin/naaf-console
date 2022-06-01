package com.feiniaojin.naaf.console.sys.dto;

import com.feiniaojin.naaf.console.data.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * CMD装配模型
 * 表名称：sys_user
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysUserCmdAssembler {

    public SysUser mapToEntity(SysUserCmd cmd) {
        SysUser sysUser = SysUserCmdMapper.INSTANCE.mapToEntity(cmd);
        //TODO 完成装配逻辑，作为mapstruct的补充，避免将逻辑泄露到service中
        return sysUser;
    }

    @Mapper(componentModel = "spring")
    public interface SysUserCmdMapper {
        SysUserCmdMapper INSTANCE = Mappers.getMapper(SysUserCmdMapper.class);

        SysUser mapToEntity(SysUserCmd cmd);
    }
}
