package com.feiniaojin.naaf.console.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：t_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class ResourceQuery implements Serializable {
    private Long id;
    private Long resourceId;
    private Long parentResourceId;
    private String path;
    private String mappingMethod;
    private String httpMethod;
    private Integer type;
    private Integer visible;
    private Integer deleted;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;
    private Long version;
    private Integer page = 1;
    private Integer pageSize = 10;
}
