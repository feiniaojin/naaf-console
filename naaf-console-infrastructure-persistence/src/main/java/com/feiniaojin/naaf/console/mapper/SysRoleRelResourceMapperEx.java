package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.data.SysRoleRelResource;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 表名称：sys_role_rel_resource自动生成的Mapper
 * 表注释：角色与受限资源关联表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface SysRoleRelResourceMapperEx {

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
    List<SysRoleRelResource> pageList(Map<String, Object> paramMap);

    /**
     * 列表获取数据
     * @param paramMap
     * @return
     */
    List<SysRoleRelResource> list(Map<String, Object> paramMap);

    /**
     * 批量删除（逻辑删除）
     * @param subtract
     */
    void deleteBatch(@Param("resourceIds") Collection resourceIds);
}