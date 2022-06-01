package com.feiniaojin.naaf.console.sys.resource.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：sys_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
public class SysResourceCmd implements Serializable {
    private String parentResourceId;
    private String path;
    private Integer type;
}
