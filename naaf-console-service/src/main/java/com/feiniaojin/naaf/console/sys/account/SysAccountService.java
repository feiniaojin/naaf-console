package com.feiniaojin.naaf.console.sys.account;


import com.feiniaojin.naaf.console.sys.account.dto.LoginCmd;
import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessView;
import com.feiniaojin.naaf.console.sys.account.dto.LogoutCmd;

/**
 * sysAccount类Service接口
 * 表名称：sys_account
 * 表注释：用户账号表
 */
public interface SysAccountService {

    void createAccount(String mobilePhone);

    LoginSuccessView login(LoginCmd cmd);

    void logout(LogoutCmd cmd);
}
