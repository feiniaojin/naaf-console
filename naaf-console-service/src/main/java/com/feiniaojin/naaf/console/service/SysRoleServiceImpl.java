package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.*;
import com.feiniaojin.naaf.console.entity.SysRole;
import com.feiniaojin.naaf.console.exception.SysRoleExceptions;
import com.feiniaojin.naaf.console.mapper.SysRoleMapper;
import com.feiniaojin.naaf.console.mapper.SysRoleMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRelResourceRepository;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRole类Service实现类
 * 表名称：sys_role
 * 表注释：角色表
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMapperEx sysRoleMapperEx;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleRelResourceRepository roleRelResourceRepository;

    @Resource
    private SysRoleCmdAssembler cmdAssembler;

    @Resource
    private SysRoleViewAssembler viewAssembler;

    @Resource
    private SysRoleAggregateFactory factory;

    @Resource
    private SysRoleAggregateRepository aggregateRepository;

    private Gson gson = new Gson();

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(SysRoleCmd cmd) {
        //根据cmd组装实体
        SysRoleAggregate aggregate = factory.newFromCmd(cmd);
        //执行创建的初始化逻辑
        aggregate.create();
        log.info("SysRole create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        aggregateRepository.save(aggregate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleCmd cmd) {
        //查询数据
        SysRole sysRole = sysRoleMapperEx.findOne(cmd.getRoleId());
        if (sysRole == null) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysRoleExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysRole input = cmdAssembler.mapToEntity(cmd);
        List<String> resourceIdList = cmd.getResourceIdList();
        //获得聚合以执行业务更新
        SysRoleAggregate aggregate = factory.fromEntity(sysRole);
        //实际执行更新操作
        aggregate.update(input, resourceIdList);
        log.info("SysRole update:cmd=[{}],sysRole=[{}]", gson.toJson(cmd), gson.toJson(sysRole));
        //保存更新操作，并清理旧的资源
        aggregateRepository.saveUpdate(aggregate);
    }

    @Override
    public SysRoleView get(SysRoleQuery query) {
        //查询数据
        String roleId = query.getRoleId();
        SysRole sysRole = sysRoleMapperEx.findOne(roleId);
        if (sysRole == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysRoleExceptions.NotFoundException();
        }
        //拼接为view
        SysRoleView view = viewAssembler.mapToView(sysRole);
        return view;
    }

    @Override
    public PageBean<SysRoleView> pageList(SysRoleQuery query) {

        PageBean<SysRoleView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = sysRoleMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<SysRole> sysRoleList = sysRoleMapperEx.pageList(paramMap);

        List<SysRoleView> views = viewAssembler.mapToViewList(sysRoleList);

        pageBean.setList(views);

        return pageBean;
    }
}
