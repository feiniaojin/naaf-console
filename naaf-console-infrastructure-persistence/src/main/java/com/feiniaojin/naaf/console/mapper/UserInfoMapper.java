package com.feiniaojin.naaf.console.mapper;

import com.feiniaojin.naaf.console.data.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Generated;

/**
 * 表名称：u_user_info自动生成的Mapper
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Generated("generator")
public interface UserInfoMapper {
    int insert(UserInfo record);
    UserInfo findOneById(@Param("id")Long id);
}