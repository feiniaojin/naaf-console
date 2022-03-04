package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.entity.SysRoleRelResource;
import com.feiniaojin.naaf.console.entity.SysUserRelRole;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

/**
 * 表名称：sys_user_rel_role自动生成的Mapper
 * 表注释：用户与角色关联表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface SysUserRelRoleMapperEx {

    /**
     * 分页查询：统计总数
     * @param paramMap
     * @return
     */
    int pageListTotal(Map<String, Object> paramMap);

    /**
     * 分页查询：分页获取数据
     * @param paramMap
     * @return
     */
    List<SysUserRelRole> pageList(Map<String, Object> paramMap);

    /**
     * 一次性加载所有符合条件的数据
     * @param paramMap
     * @return
     */
    List<SysUserRelRole> list(Map<String, Object> paramMap);
}