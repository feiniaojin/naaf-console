package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.data.SysAccountRelUser;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

/**
 * 表名称：sys_account_rel_user自动生成的Mapper
 * 表注释：账号与用户关联表
 * NOTICE:本文件由代码生成器naaf-generator生成
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface SysAccountRelUserMapperEx {

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
    List<SysAccountRelUser> pageList(Map<String, Object> paramMap);
}