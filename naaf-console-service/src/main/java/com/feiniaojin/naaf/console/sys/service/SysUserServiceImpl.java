package com.feiniaojin.naaf.console.sys.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.entity.SysUser;
import com.feiniaojin.naaf.console.sys.dto.*;
import com.feiniaojin.naaf.console.sys.exception.SysUserExceptions;
import com.feiniaojin.naaf.console.mapper.SysUserMapper;
import com.feiniaojin.naaf.console.mapper.SysUserMapperEx;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
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
 * SysUser类Service实现类
 * 表名称：sys_user
 * 表注释：用户账号表
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserMapperEx sysUserMapperEx;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysUserCmdAssembler cmdAssembler;

    @Resource
    private SysUserViewAssembler viewAssembler;

    @Resource
    private SysUserAggregateFactory factory;

    @Resource
    private SysUserAggregateRepository aggregateRepository;

    private Gson gson = new Gson();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUserCmd cmd) {
        //根据cmd组装实体
        SysUserAggregate aggregate = factory.newFromCmd(cmd);
        //执行创建的初始化逻辑
        aggregate.create();
        log.info("SysUser create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        sysUserRepository.save(aggregate.getEntity());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserCmd cmd) {
        //查询数据
        SysUser sysUser = sysUserMapperEx.findOneByBizId(cmd.getUid());
        if (sysUser == null) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysUserExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysUser input = cmdAssembler.mapToEntity(cmd);
        //执行业务更新
        SysUserAggregate aggregate = factory.fromEntity(sysUser);
        aggregate.update(input);
        //保存
        log.info("SysUser update:cmd=[{}],sysUser=[{}]", gson.toJson(cmd), gson.toJson(sysUser));
        sysUserRepository.save(aggregate.getEntity());
    }

    @Override
    public SysUserView get(SysUserQuery query) {
        //查询数据
        String uid = query.getUid();
        SysUser sysUser = sysUserMapperEx.findOneByBizId(uid);
        if (sysUser == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysUserExceptions.NotFoundException();
        }
        //拼接为view
        SysUserView view = viewAssembler.mapToView(sysUser);
        return view;
    }

    @Override
    public PageBean<SysUserView> pageList(SysUserQuery query) {

        PageBean<SysUserView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = sysUserMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<SysUser> sysUserList = sysUserMapperEx.pageList(paramMap);

        List<SysUserView> views = viewAssembler.mapToViewList(sysUserList);

        pageBean.setList(views);

        return pageBean;
    }

    @Override
    @Transactional
    public void assignRoles(SysUserCmd cmd) {
        //查询数据
        SysUser sysUser = sysUserMapperEx.findOneByBizId(cmd.getUid());
        if (sysUser == null) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysUserExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysUser input = cmdAssembler.mapToEntity(cmd);
        //执行业务更新
        SysUserAggregate aggregate = factory.fromEntity(sysUser);
        aggregate.assignRoles(input);
        log.info("SysUser assignRoles:cmd=[{}],sysUser=[{}]", gson.toJson(cmd), gson.toJson(sysUser));
        //保存更新操作，并清理旧的资源
        aggregateRepository.saveUpdate(aggregate);
    }
}
