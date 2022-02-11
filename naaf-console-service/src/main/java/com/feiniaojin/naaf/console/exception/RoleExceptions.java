package com.feiniaojin.naaf.console.exception;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * Role类异常及异常码
 * 表名称：t_role
 * 表注释：角色表
 */
public class RoleExceptions {
    @ExceptionMapper(code = 3001, msg = "创建异常")
    public static class CreateException extends RuntimeException {
    }

    @ExceptionMapper(code = 3404, msg = "数据不存在")
    public static class NotFoundException extends RuntimeException {
    }
}