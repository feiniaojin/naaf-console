package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.dto.UserInfoQuery;
import com.feiniaojin.naaf.console.dto.UserInfoView;
import com.feiniaojin.naaf.console.mapper.UserInfoMapper;
import com.feiniaojin.naaf.console.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserInfo类Service实现类
 * 表名称：t_user_info
 * 表注释：用户信息表
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public void create(UserInfoCmd command) {

    }

    @Override
    public void update(UserInfoCmd command) {

    }

    @Override
    public UserInfoView get(UserInfoQuery query) {
        return null;
    }

    @Override
    public PageBean<UserInfoView> pageList(UserInfoQuery query) {
        return null;
    }
}