package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.SysResourceAssembler;
import com.feiniaojin.naaf.console.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.dto.SysResourceQuery;
import com.feiniaojin.naaf.console.dto.SysResourceView;
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

    private Gson gson = new Gson();

    @Override
    public void create(SysResourceCmd cmd) {
        SysResource sysResource = SysResourceAssembler.INSTANCE.mapToEntity(cmd);
        sysResource.setResourceId(idGeneratorAdapter.getUid());
        sysResource.setParentResourceId(0L);
        sysResource.setType(0);
        sysResource.setVisible(1);
        log.info("SysResource create:cmd=[{}],sysResource=[{}]", gson.toJson(cmd), gson.toJson(sysResource));
        sysResourceRepository.save(sysResource);
    }

    @Override
    public void update(SysResourceCmd cmd) {

    }

    @Override
    public SysResourceView get(SysResourceQuery query) {

        Long id = query.getId();

        SysResource sysResource = sysResourceMapper.findOneById(id);

        if (sysResource == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysResourceExceptions.NotFoundException();
        }

        SysResourceView sysResourceView = SysResourceAssembler.INSTANCE.mapToView(sysResource);

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

        List<SysResourceView> views = SysResourceAssembler.INSTANCE.mapToViewList(sysResourceList);

        pageBean.setList(views);

        return pageBean;
    }
}
