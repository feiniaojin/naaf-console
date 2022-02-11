package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserCmd;
import com.feiniaojin.naaf.console.dto.UserQuery;
import com.feiniaojin.naaf.console.dto.UserView;
import com.feiniaojin.naaf.console.mapper.UserMapper;
import com.feiniaojin.naaf.console.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User类Service实现类
 * 表名称：t_user
 * 表注释：用户账号表
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRepository userRepository;

    @Override
    public void create(UserCmd command) {

    }

    @Override
    public void update(UserCmd command) {

    }

    @Override
    public UserView get(UserQuery query) {
        return null;
    }

    @Override
    public PageBean<UserView> pageList(UserQuery query) {
        return null;
    }
}