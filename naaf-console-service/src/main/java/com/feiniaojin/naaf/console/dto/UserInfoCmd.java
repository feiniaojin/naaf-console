package com.feiniaojin.naaf.console.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 表名称：u_user_info
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class UserInfoCmd implements Serializable {
    private Long id;
    private String uid;
    private String mobilePhone;
    private String email;
    private String userName;
    private String profileImgUrl;
    private Integer deleted;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;
    private Long version;
    private List<String> roleIds;
}
