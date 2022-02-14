package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * CMD装配模型
 * 表名称：sys_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysResourceCmdAssembler {

    public SysResource mapToEntity(SysResourceCmd cmd) {
        SysResource sysResource = SysResourceCmdMapper.INSTANCE.mapToEntity(cmd);
        //TODO 完成装配逻辑，作为mapstruct的补充，避免将逻辑泄露到service中
        return sysResource;
    }

    @Mapper(componentModel = "spring")
    public interface SysResourceCmdMapper {
        SysResourceCmdMapper INSTANCE = Mappers.getMapper(SysResourceCmdMapper.class);

        SysResource mapToEntity(SysResourceCmd cmd);
    }
}
