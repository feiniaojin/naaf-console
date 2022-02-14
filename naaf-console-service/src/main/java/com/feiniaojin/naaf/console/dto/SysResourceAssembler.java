package com.feiniaojin.naaf.console.dto;

import com.feiniaojin.naaf.console.entity.SysResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表名称：sys_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Mapper
public interface SysResourceAssembler {

    SysResourceAssembler INSTANCE = Mappers.getMapper(SysResourceAssembler.class);

    SysResource mapToEntity(SysResourceCmd cmd);

    SysResourceView mapToView(SysResource entity);

    List<SysResourceView> mapToViewList(List<SysResource> entityList);
}
