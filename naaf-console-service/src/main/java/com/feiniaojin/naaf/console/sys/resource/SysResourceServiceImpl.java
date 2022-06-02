package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.console.sys.resource.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.sys.resource.dto.SysResourceViewAssembler;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysResource类Service实现类
 * 表名称：sys_resource
 * 表注释：受限资源表
 */
@Service
@Slf4j
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceViewAssembler viewAssembler;

    @Resource
    private ResourceAggregateRepository aggregateRepository;

    @Resource
    private ResourceAggregateFactory aggregateFactory;

    private Gson gson = new Gson();

    @Override
    public void create(SysResourceCmd cmd) {
        ResourceAggregate aggregate = aggregateFactory.newAggregate(new ResourceId(cmd.getParentResourceId()),
                cmd.getPath(),
                cmd.getType());

        aggregateRepository.save(aggregate);
    }

}
