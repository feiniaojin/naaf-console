package com.feiniaojin.naaf.console.sys.account.dto;

import lombok.Data;

@Data
public class LoginCmd {
    private String mobilePhone;
    private String password;
}
