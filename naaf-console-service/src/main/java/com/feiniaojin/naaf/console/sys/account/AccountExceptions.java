package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.ngr.def.ExceptionMapper;

public class AccountExceptions {

    @ExceptionMapper(code = 401, msg = "账号不存在")
    public static class AccountNotExistException extends RuntimeException {

    }

    @ExceptionMapper(code = 401, msg = "密码错误")
    public static class PasswordIncorrectException extends RuntimeException {

    }
}
