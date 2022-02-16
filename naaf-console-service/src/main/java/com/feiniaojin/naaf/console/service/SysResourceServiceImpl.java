package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.*;
import com.feiniaojin.naaf.console.entity.SysResource;
import com.feiniaojin.naaf.console.exception.SysResourceExceptions;
import com.feiniaojin.naaf.console.mapper.SysResourceMapper;
import com.feiniaojin.naaf.console.mapper.SysResourceMapperEx;
import com.feiniaojin.naaf.console.repository.SysResourceRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * SysResource类Service实现类
 * 表名称：sys_resource
 * 表注释：受限资源表
 */
@Service
@Slf4j
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Resource
    private SysResourceMapperEx sysResourceMapperEx;

    @Resource
    private SysResourceRepository sysResourceRepository;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    @Resource
    private SysResourceCmdAssembler cmdAssembler;

    @Resource
    private SysResourceViewAssembler viewAssembler;

    @Resource
    private SysResourceAggregateFactory factory;

    private Gson gson = new Gson();

    @Override
    public void create(SysResourceCmd cmd) {
        SysResourceAggregate aggregate = factory.newFromCmd(cmd);
        //执行创建的初始化逻辑
        aggregate.create();
        log.info("SysResource create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        sysResourceRepository.save(aggregate.getEntity());
    }

    @Override
    public void update(SysResourceCmd cmd) {
        //查询数据
        Optional<SysResource> byId = sysResourceRepository.findById(cmd.getId());
        if (!byId.isPresent()) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysResourceExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysResource input = cmdAssembler.mapToEntity(cmd);
        //获取数据库对应实体
        SysResource sysResource = byId.get();
        //执行业务更新
        SysResourceAggregate aggregate = factory.fromEntity(sysResource);
        aggregate.update(input);
        //保存
        log.info("SysResource update:cmd=[{}],sysResource=[{}]", gson.toJson(cmd), gson.toJson(sysResource));
        sysResourceRepository.save(aggregate.getEntity());
    }

    @Override
    public SysResourceView get(SysResourceQuery query) {
        //查询数据
        Long id = query.getId();
        SysResource sysResource = sysResourceMapper.findOneById(id);
        if (sysResource == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysResourceExceptions.NotFoundException();
        }
        //拼接为view
        SysResourceView sysResourceView = viewAssembler.mapToView(sysResource);
        return sysResourceView;
    }

    @Override
    public PageBean<SysResourceView> pageList(SysResourceQuery query) {

        PageBean<SysResourceView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = sysResourceMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<SysResource> sysResourceList = sysResourceMapperEx.pageList(paramMap);

        List<SysResourceView> views = viewAssembler.mapToViewList(sysResourceList);

        pageBean.setList(views);

        return pageBean;
    }
}
