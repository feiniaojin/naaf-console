package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.ResourceCmd;
import com.feiniaojin.naaf.console.dto.ResourceQuery;
import com.feiniaojin.naaf.console.dto.ResourceView;
import com.feiniaojin.naaf.console.mapper.ResourceMapper;
import com.feiniaojin.naaf.console.repository.ResourceRepository;
import org.springframework.data.domain.DomainEvents;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Resource类Service实现类
 * 表名称：t_resource
 * 表注释：受限资源表
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ResourceMapper resourceMapper;
    @Resource
    private ResourceRepository resourceRepository;

    @Override
    public void create(ResourceCmd command) {

    }
    @Override
    public void update(ResourceCmd command) {

    }

    @Override
    public ResourceView get(ResourceQuery query) {
        return null;
    }

    @Override
    public PageBean<ResourceView> pageList(ResourceQuery query) {
        return null;
    }
}