package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.dto.SysRoleCmdAssembler;
import com.feiniaojin.naaf.console.entity.SysRole;
import com.feiniaojin.naaf.console.entity.SysRoleRelResource;
import com.feiniaojin.naaf.console.mapper.SysRoleRelResourceMapperEx;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysRoleAggregateFactory {

    @Resource
    private SysRoleCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    @Resource
    private SysRoleRelResourceMapperEx relResourceMapperEx;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public SysRoleAggregate newFromCmd(SysRoleCmd cmd) {
        //根据cmd组装实体
        SysRole mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setRoleId(idGeneratorAdapter.getUid());
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.setEntity(mapToEntity);
        //创建角色与资源的映射
        List<String> resourceIdList = cmd.getResourceIdList();
        if (!CollectionUtils.isEmpty(resourceIdList)) {
            List<SysRoleRelResource> list = new ArrayList<>();
            for (String resourceId : resourceIdList) {
                SysRoleRelResource relResource = new SysRoleRelResource();
                relResource.setRoleId(mapToEntity.getRoleId());
                relResource.setResourceId(resourceId);
                list.add(relResource);
            }
            aggregate.setRoleRelResourceList(list);
        }
        return aggregate;
    }

    /**
     * 从实体创建聚合
     *
     * @param newEntity
     * @return
     */
    public SysRoleAggregate fromEntity(SysRole newEntity) {
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.setEntity(newEntity);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleId", newEntity.getRoleId());
        List<SysRoleRelResource> roleRelResources = relResourceMapperEx.list(paramMap);
        aggregate.setRoleRelResourceList(roleRelResources);
        return aggregate;
    }
}
