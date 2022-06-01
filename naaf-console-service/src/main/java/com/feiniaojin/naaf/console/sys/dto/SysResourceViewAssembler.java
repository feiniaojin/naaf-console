package com.feiniaojin.naaf.console.sys.dto;

import com.feiniaojin.naaf.console.data.SysResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity装配为view
 * 表名称：sys_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Component
public class SysResourceViewAssembler {

    public SysResourceView mapToView(SysResource entity) {
        SysResourceView sysResourceView = SysResourceViewMapper.INSTANCE.mapToView(entity);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return sysResourceView;
    }

    public List<SysResourceView> mapToViewList(List<SysResource> entityList) {
        List<SysResourceView> viewList = SysResourceViewMapper.INSTANCE.mapToViewList(entityList);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return viewList;
    }

    @Mapper(componentModel = "spring")
    public interface SysResourceViewMapper {
        SysResourceViewMapper INSTANCE = Mappers.getMapper(SysResourceViewMapper.class);

        SysResourceView mapToView(SysResource entity);

        List<SysResourceView> mapToViewList(List<SysResource> entityList);
    }
}
