package com.feiniaojin.naaf.console.uinfo.command.events;

import lombok.Builder;
import lombok.Data;

/**
 * 用于command通知query
 *
 * @author qinyujie3
 */
@Data
@Builder
public class CqrsEvent {
    private String uid;
    private String email;
    private String mobilePhone;
    private String userName;
    private String eventType;
    private String eventId;
}
