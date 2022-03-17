package com.feiniaojin.naaf.console.sys.events;

import lombok.Builder;
import lombok.Data;

/**
 * 用户账号创建事件
 *
 * @author qinyujie3
 */
@Data
@Builder
public class SysUserEvent {
    private String uid;
    private String email;
    private String mobilePhone;
    private String userName;
    private String eventType;
    private String eventId;
}
