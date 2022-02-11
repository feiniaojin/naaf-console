package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.RoleCmd;
import com.feiniaojin.naaf.console.dto.RoleQuery;
import com.feiniaojin.naaf.console.dto.RoleView;
import com.feiniaojin.naaf.console.mapper.RoleMapper;
import com.feiniaojin.naaf.console.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Role类Service实现类
 * 表名称：t_role
 * 表注释：角色表
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleRepository roleRepository;

    @Override
    public void create(RoleCmd command) {

    }

    @Override
    public void update(RoleCmd command) {

    }

    @Override
    public RoleView get(RoleQuery query) {
        return null;
    }

    @Override
    public PageBean<RoleView> pageList(RoleQuery query) {
        return null;
    }
}