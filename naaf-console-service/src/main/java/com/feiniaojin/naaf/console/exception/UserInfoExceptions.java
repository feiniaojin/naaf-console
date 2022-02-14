package com.feiniaojin.naaf.console.exception;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

/**
 * UserInfo类异常及异常码
 * 表名称：u_user_info
 * 表注释：用户信息表
 */
public class UserInfoExceptions {
    @ExceptionMapper(code = 1404,msg = "找不到该资源")
    public static final class NotFoundException extends RuntimeException{}
}