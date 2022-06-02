package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.console.data.SysResource;
import com.feiniaojin.naaf.console.mapper.SysResourceMapper;
import com.feiniaojin.naaf.console.repository.SysResourceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Component
public class ResourceAggregateRepository {

    @Resource
    private SysResourceRepository sysResourceRepository;

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Resource
    private ResourceAggregateAssembler aggregateAssembler;

    public ResourceAggregate load() {
        return null;
    }

    public void save(ResourceAggregate aggregate) {
        SysResource sysResource = aggregateAssembler.mapToData(aggregate);
        sysResourceRepository.save(sysResource);
    }
}
