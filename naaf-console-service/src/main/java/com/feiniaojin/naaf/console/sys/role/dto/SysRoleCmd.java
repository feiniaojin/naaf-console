package com.feiniaojin.naaf.console.sys.role.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 表名称：sys_role
 * 表注释：角色表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class SysRoleCmd implements Serializable {
    private Long id;
    private String roleId;
    private String roleName;
    private Integer deleted;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;
    private Long version;
    private List<String> resourceIdList;
}
