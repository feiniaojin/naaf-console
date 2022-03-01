package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.dto.UserInfoQuery;
import com.feiniaojin.naaf.console.dto.UserInfoView;

/**
 * userInfo类Service接口
 * 表名称：u_user_info
 * 表注释：用户信息表
 */
public interface UserInfoService {

    /**
     * 创建
     *
     * @param cmd
     */
    void create(UserInfoCmd cmd);

    /**
     * 更新
     *
     * @param cmd
     */
    void update(UserInfoCmd cmd);

    /**
     * 根据业务主键查询
     *
     * @param query
     */
    UserInfoView get(UserInfoQuery query);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageBean<UserInfoView> pageList(UserInfoQuery query);
}
