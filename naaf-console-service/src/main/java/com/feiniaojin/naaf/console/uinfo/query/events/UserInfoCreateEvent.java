package com.feiniaojin.naaf.console.uinfo.query.events;

import lombok.Data;

/**
 * 用户信息创建事件
 *
 * @author qinyujie3
 */
@Data
public class UserInfoCreateEvent {
    private String uid;
    private String email;
    private String mobilePhone;
    private String userName;
}
