package com.feiniaojin.naaf.console.sys.exception;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * SysUser类异常及异常码
 * 表名称：sys_user
 * 表注释：用户账号表
 */
public class SysUserExceptions {
    @ExceptionMapper(code = 1404,msg = "找不到该资源")
    public static final class NotFoundException extends RuntimeException{}
}