package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserCmd;
import com.feiniaojin.naaf.console.dto.UserQuery;
import com.feiniaojin.naaf.console.dto.UserView;

/**
 * user类Service接口
 * 表名称：t_user
 * 表注释：用户账号表
 */
public interface UserService {

    /**
     * 创建
     *
     * @param command
     */
    void create(UserCmd command);

    /**
     * 更新
     *
     * @param command
     */
    void update(UserCmd command);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    UserView get(UserQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageBean<UserView> pageList(UserQuery query);
}
