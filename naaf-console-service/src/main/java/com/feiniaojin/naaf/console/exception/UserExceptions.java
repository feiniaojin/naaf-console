package com.feiniaojin.naaf.console.exception;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * User类异常及异常码
 * 表名称：t_user
 * 表注释：用户账号表
 */
public class UserExceptions {
    @ExceptionMapper(code = 3001, msg = "创建异常")
    public static class CreateException extends RuntimeException {
    }

    @ExceptionMapper(code = 3404, msg = "数据不存在")
    public static class NotFoundException extends RuntimeException {
    }
}