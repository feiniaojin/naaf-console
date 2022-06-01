package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.console.data.SysRoleRelResource;
import com.feiniaojin.naaf.console.mapper.SysRoleRelResourceMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRelResourceRepository;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SysRoleAggregateRepository {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleRelResourceRepository roleRelResourceRepository;

    @Resource
    private SysRoleRelResourceMapperEx relResourceMapperEx;

    public void save(SysRoleAggregate aggregate) {
        sysRoleRepository.save(aggregate.getEntity());
        List<SysRoleRelResource> roleRelResourceList = aggregate.getRoleRelResourceList();
        if (CollectionUtils.isNotEmpty(roleRelResourceList)) {
            roleRelResourceRepository.saveAll(roleRelResourceList);
        }
    }

    public void saveUpdate(SysRoleAggregate aggregate) {

        String roleId = aggregate.getEntity().getRoleId();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        List<SysRoleRelResource> list = relResourceMapperEx.list(paramMap);

        //数据库没有关联信息，直接保存实体返回
        if (CollectionUtils.isEmpty(list)) {
            //保存实体
            sysRoleRepository.save(aggregate.getEntity());
            //如果有关联，则保存关联
            if (CollectionUtils.isNotEmpty(aggregate.getRoleRelResourceList())) {
                roleRelResourceRepository.saveAll(aggregate.getRoleRelResourceList());
            }
            return;
        }

        //新的聚合中不再有关联关系，直接把数据库所有的关联置为失效
        if (CollectionUtils.isEmpty(aggregate.getRoleRelResourceList())) {
            //保存实体
            sysRoleRepository.save(aggregate.getEntity());
            //如果有关联，则置为失效，再保存关联
            if (CollectionUtils.isNotEmpty(aggregate.getRoleRelResourceList())) {
                list.stream().forEach(e -> e.setDeleted(1));
                roleRelResourceRepository.saveAll(list);
            }
            return;
        }

        List<String> resourceIdList = aggregate.getResourceIdList();
        Set<String> dbResourceIds = list.stream().map(rel -> rel.getResourceId()).collect(Collectors.toSet());
        Map<String, SysRoleRelResource> resourceMap = list.stream().collect(Collectors.toMap(e -> e.getResourceId(), e -> e));

        //数据库不存在，创建关联
        List<String> toCreate = new ArrayList<>();
        //数据库已存在，修改为已生效
        List<String> toUpdate = new ArrayList<>();
        //不需要修改的
        List<String> notModifyList = new ArrayList<>();
        for (String resourceId : resourceIdList) {
            if (!dbResourceIds.contains(resourceId)) {
                toCreate.add(resourceId);
                continue;
            }
            SysRoleRelResource relResource = resourceMap.get(resourceId);
            if (relResource.getDeleted() == 1) {
                toUpdate.add(resourceId);
                continue;
            }
            notModifyList.add(resourceId);
        }

        //数据库已存在，修改为失效
        if (CollectionUtils.isNotEmpty(notModifyList)) {
            Iterator<String> it = notModifyList.iterator();
            while (it.hasNext()) {
                dbResourceIds.remove(it.next());
            }
        }
        if (CollectionUtils.isNotEmpty(toUpdate)) {
            Iterator<String> iterator = toUpdate.iterator();
            while (iterator.hasNext()) {
                dbResourceIds.remove(iterator.next());
            }
        }
        //数据库已存在，修改为有效
        List<String> toDelete = new ArrayList<>(dbResourceIds);
        //处理需要更新为有效的
        if (CollectionUtils.isNotEmpty(toUpdate)) {
            List<SysRoleRelResource> rels = new ArrayList<>();
            for (String resourceId : toUpdate) {
                SysRoleRelResource relResource = resourceMap.get(resourceId);
                relResource.setDeleted(0);
                rels.add(relResource);
            }
            roleRelResourceRepository.saveAll(rels);
        }

        //处理需要删除的
        if (CollectionUtils.isNotEmpty(toDelete)) {
            List<SysRoleRelResource> rels = new ArrayList<>();
            for (String resourceId : toDelete) {
                SysRoleRelResource relResource = resourceMap.get(resourceId);
                relResource.setDeleted(1);
                rels.add(relResource);
            }
            roleRelResourceRepository.saveAll(rels);
        }

        //处理需要创建的
        if (CollectionUtils.isNotEmpty(toCreate)) {
            List<SysRoleRelResource> rels = new ArrayList<>();
            for (String resourceId : toCreate) {
                SysRoleRelResource relResource = new SysRoleRelResource();
                relResource.setRoleId(roleId);
                relResource.setResourceId(resourceId);
                relResource.setDeleted(0);
                rels.add(relResource);
            }
            roleRelResourceRepository.saveAll(rels);
        }
        //保存
        sysRoleRepository.save(aggregate.getEntity());
    }
}
