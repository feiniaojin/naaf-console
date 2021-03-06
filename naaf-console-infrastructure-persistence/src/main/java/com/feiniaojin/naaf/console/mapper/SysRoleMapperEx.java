package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.data.SysRole;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

/**
 * 表名称：sys_role自动生成的Mapper
 * 表注释：角色表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface SysRoleMapperEx {

    /**
     * 分页查询：统计总数
     *
     * @param paramMap
     * @return
     */
    int pageListTotal(Map<String, Object> paramMap);

    /**
     * 分页查询：分页获取数据
     *
     * @param paramMap
     * @return
     */
    List<SysRole> pageList(Map<String, Object> paramMap);

    /**
     * 根据业务id查询
     *
     * @param bizId
     * @return
     */
    SysRole findOne(@Param("roleId") String roleId);
}