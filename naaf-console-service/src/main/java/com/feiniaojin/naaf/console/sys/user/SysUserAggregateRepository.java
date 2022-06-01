package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.console.data.SysUserRelRole;
import com.feiniaojin.naaf.console.mapper.SysUserRelRoleMapperEx;
import com.feiniaojin.naaf.console.repository.SysUserRelRoleRepository;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SysUserAggregateRepository {

    @Resource
    private SysUserRepository userRepository;

    @Resource
    private SysUserRelRoleRepository relRoleRepository;

    @Resource
    private SysUserRelRoleMapperEx relRoleMapperEx;

    public void saveUpdate(SysUserAggregate aggregate) {

        String uid = aggregate.getEntity().getUid();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        List<SysUserRelRole> list = relRoleMapperEx.list(paramMap);

        //数据库没有关联信息，直接保存实体返回
        if (CollectionUtils.isEmpty(list)) {
            //保存实体
            userRepository.save(aggregate.getEntity());
            //如果有关联，则保存关联
            if (CollectionUtils.isNotEmpty(aggregate.getUserRelRoleList())) {
                relRoleRepository.saveAll(aggregate.getUserRelRoleList());
            }
            return;
        }

        //新的聚合中不再有关联关系，直接把数据库所有的关联置为失效
        if (CollectionUtils.isEmpty(aggregate.getUserRelRoleList())) {
            //保存实体
            userRepository.save(aggregate.getEntity());
            //如果有关联，则置为失效，再保存关联
            if (CollectionUtils.isNotEmpty(aggregate.getUserRelRoleList())) {
                list.stream().forEach(e -> e.setDeleted(1));
                relRoleRepository.saveAll(list);
            }
            return;
        }

        List<String> roleIdList = aggregate.getRoleIdList();
        Set<String> dbRoleIds = list.stream().map(rel -> rel.getRoleId()).collect(Collectors.toSet());
        Map<String, SysUserRelRole> roleMap = list.stream().collect(Collectors.toMap(e -> e.getRoleId(), e -> e));

        //数据库不存在，创建关联
        List<String> toCreate = new ArrayList<>();
        //数据库已存在，修改为已生效
        List<String> toUpdate = new ArrayList<>();
        //不需要修改的
        List<String> notModifyList = new ArrayList<>();
        for (String roleId : roleIdList) {
            if (!dbRoleIds.contains(roleId)) {
                toCreate.add(roleId);
                continue;
            }
            SysUserRelRole relRole = roleMap.get(roleId);
            if (relRole.getDeleted() == 1) {
                toUpdate.add(roleId);
                continue;
            }
            notModifyList.add(roleId);
        }

        //数据库已存在，修改为失效
        if (CollectionUtils.isNotEmpty(notModifyList)) {
            Iterator<String> it = notModifyList.iterator();
            while (it.hasNext()) {
                dbRoleIds.remove(it.next());
            }
        }
        if (CollectionUtils.isNotEmpty(toUpdate)) {
            Iterator<String> iterator = toUpdate.iterator();
            while (iterator.hasNext()) {
                dbRoleIds.remove(iterator.next());
            }
        }
        //数据库已存在，修改为有效
        List<String> toDelete = new ArrayList<>(dbRoleIds);
        //处理需要更新为有效的
        if (CollectionUtils.isNotEmpty(toUpdate)) {
            List<SysUserRelRole> rels = new ArrayList<>();
            for (String roleId : toUpdate) {
                SysUserRelRole relResource = roleMap.get(roleId);
                relResource.setDeleted(0);
                rels.add(relResource);
            }
            relRoleRepository.saveAll(rels);
        }

        //处理需要删除的
        if (CollectionUtils.isNotEmpty(toDelete)) {
            List<SysUserRelRole> rels = new ArrayList<>();
            for (String roleId : toDelete) {
                SysUserRelRole relRole = roleMap.get(roleId);
                relRole.setDeleted(1);
                rels.add(relRole);
            }
            relRoleRepository.saveAll(rels);
        }

        //处理需要创建的
        if (CollectionUtils.isNotEmpty(toCreate)) {
            List<SysUserRelRole> rels = new ArrayList<>();
            for (String roleId : toCreate) {
                SysUserRelRole relRole = new SysUserRelRole();
                relRole.setRoleId(roleId);
                relRole.setUid(uid);
                relRole.setDeleted(0);
                rels.add(relRole);
            }
            relRoleRepository.saveAll(rels);
        }
        //保存
        userRepository.save(aggregate.getEntity());
    }
}
