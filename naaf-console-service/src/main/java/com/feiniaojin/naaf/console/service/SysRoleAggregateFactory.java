package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.dto.SysRoleCmdAssembler;
import com.feiniaojin.naaf.console.entity.SysRole;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysRoleAggregateFactory {

    @Resource
    private SysRoleCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public SysRoleAggregate newFromCmd(SysRoleCmd cmd) {
        //根据cmd组装实体
        SysRole mapToEntity = cmdAssembler.mapToEntity(cmd);
//        例如: mapToEntity.setResourceId(idGeneratorAdapter.getUid());
        mapToEntity.setRoleId(idGeneratorAdapter.getUid());
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.setEntity(mapToEntity);
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
        return aggregate;
    }
}
