package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * SysResource类异常及异常码
 * 表名称：sys_resource
 * 表注释：受限资源表
 */
public class SysResourceExceptions {
    @ExceptionMapper(code = 1404,msg = "找不到该资源")
    public static final class NotFoundException extends RuntimeException{}
}