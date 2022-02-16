package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysRoleRelResource;
import com.feiniaojin.naaf.console.mapper.SysRoleRelResourceMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRelResourceRepository;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SysRoleAggregateRepository {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleRelResourceRepository roleRelResourceRepository;

    @Resource
    private SysRoleRelResourceMapperEx relResourceMapperEx;

    private Gson gson = new Gson();

    public void save(SysRoleAggregate aggregate) {
        sysRoleRepository.save(aggregate.getEntity());
        roleRelResourceRepository.saveAll(aggregate.getRoleRelResourceList());
    }

    public void saveUpdate(SysRoleAggregate aggregate, List<SysRoleRelResource> oldRoleRelResourceList) {

        //需要删除的resource
        Set<String> oldResourceIdSet = oldRoleRelResourceList.stream()
                .map(r -> r.getResourceId())
                .collect(Collectors.toSet());
        List<String> newResourceIdList = aggregate.getResourceIdList();
        //待删除
        Collection subtract = CollectionUtils.subtract(oldResourceIdSet, newResourceIdList);
        relResourceMapperEx.deleteBatch(subtract);
        //保存
        sysRoleRepository.save(aggregate.getEntity());
        //需要新增的resource
        roleRelResourceRepository.saveAll(aggregate.getRoleRelResourceList());
    }
}
