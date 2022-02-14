package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表名称：sys_user
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Mapper
public interface SysUserAssembler {

    SysUserAssembler INSTANCE = Mappers.getMapper(SysUserAssembler.class);

    SysUser mapToEntity(SysUserCmd cmd);

    SysUserView mapToView(SysUser entity);

    List<SysUserView> mapToViewList(List<SysUser> entityList);
}
