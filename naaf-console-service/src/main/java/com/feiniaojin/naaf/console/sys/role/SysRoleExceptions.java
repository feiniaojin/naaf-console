package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * SysRole类异常及异常码
 * 表名称：sys_role
 * 表注释：角色表
 */
public class SysRoleExceptions {
    @ExceptionMapper(code = 1404,msg = "找不到该资源")
    public static final class NotFoundException extends RuntimeException{}
}