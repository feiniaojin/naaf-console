package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

/**
 * 表名称：sys_user自动生成的Mapper
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface SysUserMapperEx {

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
    List<SysUser> pageList(Map<String, Object> paramMap);

    /**
     * 通过业务id查询
     *
     * @param uid
     * @return
     */
    SysUser findOne(@Param("uid") String uid);
}