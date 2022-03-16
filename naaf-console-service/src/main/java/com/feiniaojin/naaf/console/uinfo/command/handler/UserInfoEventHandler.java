package com.feiniaojin.naaf.console.uinfo.command.handler;

import com.feiniaojin.naaf.console.entity.UserInfoEvent;

public interface UserInfoEventHandler {
    void handle(UserInfoEvent e);
}
