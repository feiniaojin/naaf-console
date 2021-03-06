package com.feiniaojin.naaf.console.sys.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：sys_user
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class SysUserView implements Serializable {
    private Long id;
    private String uid;
    private String mobilePhone;
    private String email;
    private String userName;
    private String password;
    private String salt;
    private Integer status;
    private Integer deleted;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;
    private Long version;
}
