package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表名称：sys_role
 * 表注释：角色表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Mapper
public interface SysRoleAssembler {

    SysRoleAssembler INSTANCE = Mappers.getMapper(SysRoleAssembler.class);

    SysRole mapToEntity(SysRoleCmd cmd);

    SysRoleView mapToView(SysRole entity);

    List<SysRoleView> mapToViewList(List<SysRole> entityList);
}
