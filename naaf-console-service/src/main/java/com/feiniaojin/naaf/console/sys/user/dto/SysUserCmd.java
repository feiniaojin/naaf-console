package com.feiniaojin.naaf.console.sys.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表名称：sys_user
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class SysUserCmd implements Serializable {
    /**
     * 用户id,业务使用
     */
    private String uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String profileImgUrl;

    private List<String> roleIdList;
}
