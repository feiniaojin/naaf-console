package com.feiniaojin.naaf.protocol.web.controller;

import com.feiniaojin.naaf.console.sys.account.SysAccountService;
import com.feiniaojin.naaf.console.sys.account.dto.LoginCmd;
import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessView;
import com.feiniaojin.naaf.console.sys.account.dto.LogoutCmd;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SysAccount类Controller类
 * 表名称：sys_account
 * 表注释：用户账号表
 */
@RestController
@RequestMapping("/account")
public class SysAccountController {

    @Resource
    private SysAccountService accountService;

    @PostMapping("/login")
    public LoginSuccessView login(LoginCmd cmd) {
        LoginSuccessView successView = accountService.login(cmd);
        return successView;
    }

    @PostMapping("/logout")
    public void logout(LogoutCmd cmd) {
        accountService.logout(cmd);
    }
}
