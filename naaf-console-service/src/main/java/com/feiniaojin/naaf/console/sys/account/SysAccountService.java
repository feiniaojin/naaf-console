package com.feiniaojin.naaf.console.sys.account;


import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessView;

/**
 * sysAccount类Service接口
 * 表名称：sys_account
 * 表注释：用户账号表
 */
public interface SysAccountService {

    void createAccount(String mobilePhone);

    LoginSuccessView login(String mobilePhone, String password);
}
