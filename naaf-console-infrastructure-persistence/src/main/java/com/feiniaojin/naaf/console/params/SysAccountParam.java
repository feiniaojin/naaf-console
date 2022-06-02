package com.feiniaojin.naaf.console.params;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SysAccountParam {
    private String accountId;
    private String mobilePhone;
    private String token;
}
